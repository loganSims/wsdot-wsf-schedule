/*
 * Copyright (c) 2015 Washington State Department of Transportation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */

package gov.wa.wsdot.wsf.schedule;

import gov.wa.wsdot.wsf.schedule.shared.CacheFlushDate;
import gov.wa.wsdot.wsf.schedule.shared.Dates;
import gov.wa.wsdot.wsf.schedule.shared.FerriesRoute;
import gov.wa.wsdot.wsf.schedule.shared.FerriesRouteAlert;
import gov.wa.wsdot.wsf.schedule.shared.FerriesScheduleTimes;
import gov.wa.wsdot.wsf.schedule.shared.FerriesTerminal;
import gov.wa.wsdot.wsf.schedule.shared.RouteDetailsResponse;
import gov.wa.wsdot.wsf.schedule.shared.ScheduleResponse;
import gov.wa.wsdot.wsf.schedule.util.DateUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * The App class queries Washington State Ferry Schedule data for departures, alerts and more.
 * <p>
 * The WSDOT Traveler API provides a single gateway to all of WSDOT's data. Most of the REST
 * operations require that an API Access Code be passed as part of the URL string. In order
 * to get a valid API Access Code, please register your email address with the WSDOT Traveler
 * API at the link below.
 * 
 * @see <a href="http://www.wsdot.wa.gov/traffic/api/">http://www.wsdot.wa.gov/traffic/api/</a>
 */
public class App {
    private static final String API_ACCESS_CODE = "";
    private static final String WSF_SCHEDULE_API = "http://www.wsdot.wa.gov/ferries/api/schedule/rest";
    private static final String FILE_PATH = "";

    /**
     * The main method for the program.
     * 
     * @param args  command-line arguments. Use {@code -verbose} for detailed output.
     */
    public static void main(String[] args) {
        int i = 0;
        String arg;
        boolean vflag = false;
        
        while (i < args.length && args[i].startsWith("-")) {
            arg = args[i++];
            
            if (arg.equals("-verbose")) {
                vflag = true;
            }
        }
        
        try {
            getSchedules(vflag);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves highly detailed information pertaining to the routes.
     * <p>
     * Including a trip date in the URL string will result in all routes
     * available for that date of travel being returned.
     * <p>
     * The trip date input needs to be formatted as 'YYYY-MM-DD'
     * (eg. '2015-07-24' for a trip date occurring on July 24, 2015)
     * 
     * @param vflag  print details if verbose flag is set
     * @throws IOException
     * @throws ParseException
     */
    private static void getSchedules(boolean vflag) throws IOException, ParseException {
        List<FerriesRoute> items = new ArrayList<FerriesRoute>();
        List<CacheFlushDate> cacheTime = new ArrayList<CacheFlushDate>();
        DateFormat tripDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date now = new Date();
        String tripDate = tripDateFormat.format(now);
        Gson gson = new Gson();
        String cachedDateTime = "";
        int routeCount = 0;
        
        try {
            File jsonFile = new File("WSFCacheFlushDateSchedules.js");
            FileReader fr = new FileReader(jsonFile.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fr);
            Type type = new TypeToken<List<CacheFlushDate>>(){}.getType();
            List<CacheFlushDate> cachedDateFromFile = gson.fromJson(br, type);
            cachedDateTime = cachedDateFromFile.get(0).getCacheDate();
        } catch (FileNotFoundException e) {
            System.out.println("Error opening WSFCacheFlushDateSchedules.js file.");
        }

        // Most web methods in this service are cached. Implementing caching in your
        // program is recommended in order to not query the web service when not necessary.
        // The /cacheflushdate Uri will tell you the date and time the cache was last flushed.        
        String jsonCacheFlush = readUrl(WSF_SCHEDULE_API + "/cacheflushdate");
        Type stringType = new TypeToken<String>(){}.getType();
        String latestCacheDateTime = gson.fromJson(jsonCacheFlush, stringType);

        // Write the most recent cache flush date and time to JSON file.
        cacheTime.add(new CacheFlushDate(latestCacheDateTime));
        String cacheFlushDateJson = new Gson().toJson(cacheTime);
        
        try {
            File jsonFile = new File("WSFCacheFlushDateSchedules.js");

            if (!jsonFile.exists()) {
                jsonFile.createNewFile();
            }

            FileWriter fw = new FileWriter(jsonFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(cacheFlushDateJson);
            bw.close();
            
            if (vflag) {
                System.out.println("Wrote WSFCacheFlushDateSchedules data to JSON file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Compare the cache dates. If dates are not equal then get latest data.
        if (!latestCacheDateTime.equalsIgnoreCase(cachedDateTime)) {
            String jsonRouteDetails = readUrl(WSF_SCHEDULE_API
                    + "/routedetails/" + tripDate + "?apiaccesscode="
                    + API_ACCESS_CODE);
            
            Type type = new TypeToken<List<RouteDetailsResponse>>(){}.getType();
            List<RouteDetailsResponse> routes = gson.fromJson(jsonRouteDetails, type);
            
            int routesSize = routes.size();
            for (int i = 0; i < routesSize; i++) {
                items.add(new FerriesRoute(
                        routes.get(i).getRouteID(),
                        routes.get(i).getDescription(),
                        latestCacheDateTime,
                        routes.get(i).getCrossingTime()
                        ));
                
                int routesAlertsSize = routes.get(i).getAlerts().size();
                for (int z = 0; z < routesAlertsSize; z++) {
                    items.get(i).setRouteAlert(
                            new FerriesRouteAlert(routes.get(i).getAlerts().get(z).getBulletinID(),
                                    routes.get(i).getAlerts().get(z).getPublishDate(),
                                    routes.get(i).getAlerts().get(z).getAlertDescription(),
                                    routes.get(i).getAlerts().get(z).getAlertFullTitle(),
                                    routes.get(i).getAlerts().get(z).getAlertFullText()));
                }
                
                String today = tripDateFormat.format(new Date());
                
                // Retrieve schedule data for today and next six days for each route.
                for (int j = 0; j < 7; j++) {
                    items.get(i).setDate(new Dates(DateUtil.serialize(tripDateFormat.parse(today))));
                    
                    int routeId = items.get(i).getRouteID();
                    String jsonSchedule = readUrl(WSF_SCHEDULE_API
                            + "/schedule/" + today + "/" + routeId
                            + "?apiaccesscode=" + API_ACCESS_CODE);
                    
                    type = new TypeToken<ScheduleResponse>(){}.getType();
                    ScheduleResponse schedule = gson.fromJson(jsonSchedule, type);
                    
                    int scheduleTerminalCombosSize = schedule.getTerminalCombos().size();
                    for (int k = 0; k < scheduleTerminalCombosSize; k++) {
                        items.get(i).getDate().get(j).setSailings(new FerriesTerminal(
                                schedule.getTerminalCombos().get(k).getDepartingTerminalID(),
                                schedule.getTerminalCombos().get(k).getDepartingTerminalName(),
                                schedule.getTerminalCombos().get(k).getArrivingTerminalID(),
                                schedule.getTerminalCombos().get(k).getArrivingTerminalName()
                                ));
                        
                        int scheduleTerminalCombosAnnotationsSize = schedule
                                .getTerminalCombos().get(k).getAnnotations()
                                .size();
                        
                        for (int l = 0; l < scheduleTerminalCombosAnnotationsSize; l++) {
                            items.get(i).getDate().get(j).getSailings().get(k).getAnnotations()
                                    .add(schedule.getTerminalCombos().get(k).getAnnotations().get(l));
                        }
                        
                        int scheduleTerminalCombosTimesSize = schedule.getTerminalCombos().get(k).getTimes().size();
                        
                        for (int m = 0; m < scheduleTerminalCombosTimesSize; m++) {
                            items.get(i).getDate().get(j).getSailings().get(k).getTimes()
                                    .add(new FerriesScheduleTimes(
                                            schedule.getTerminalCombos().get(k).getTimes().get(m).getDepartingTime(),
                                            schedule.getTerminalCombos().get(k).getTimes().get(m).getArrivingTime()
                                            ));
                            
                            int scheduleTerminalCombosTimesAnnotationIndexesSize = schedule
                                    .getTerminalCombos().get(k).getTimes()
                                    .get(m).getAnnotationIndexes().size();
                            
                            for (int n = 0; n < scheduleTerminalCombosTimesAnnotationIndexesSize; n++) {
                                items.get(i).getDate().get(j).getSailings().get(k).getTimes().get(m).getAnnotationIndexes()
                                        .add(schedule.getTerminalCombos().get(k).getTimes().get(m).getAnnotationIndexes().get(n));
                            }
                        }
                    }
                    
                    // Increment the date one day.
                    today = tripDateFormat.format(DateUtil.addDays(
                            tripDateFormat.parse(today), 1));
                    
                }
                
                if (vflag) {
                    routeCount = i + 1;
                    System.out.println("RouteID: " + items.get(i).getRouteID()
                            + " (" + routeCount + " of " + routesSize + ")");
                }
            }
            
            // The default behaviour that is implemented in Gson is that null object fields are ignored.
            // Adding serializeNulls provides Null object support.
            String ferriesRouteJson = new GsonBuilder().serializeNulls().create().toJson(items);
            
            try {
                File jsonFile = new File(FILE_PATH + "WSFRouteSchedules.js");
    
                if (!jsonFile.exists()) {
                    jsonFile.createNewFile();
                }
    
                FileWriter fw = new FileWriter(jsonFile.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(ferriesRouteJson);
                bw.close();
                
                if (vflag) {
                    System.out.println("Wrote WSFRouteSchedules data to JSON file.");
                }
                
                GZIPOutputStream gzos = new GZIPOutputStream(
                        new FileOutputStream(FILE_PATH + "WSFRouteSchedules.js.gz"));
                
                FileInputStream fis = new FileInputStream(FILE_PATH + "WSFRouteSchedules.js");
                
                byte[] buffer = new byte[1024];
                int len;
                
                while ((len = fis.read(buffer)) > 0) {
                    gzos.write(buffer, 0, len);
                }
                
                fis.close();
                gzos.finish();
                gzos.close();
                
                if (vflag) {
                    System.out.println("Wrote WSFRouteSchedules data to compressed JSON file.");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (vflag) {
                System.out.println("Data files are up to date. Nothing to do.");
            }
        }
    }
    
    /**
     * Retrieves the contents of the passed URL string.
     * 
     * @param urlString  the URL string to connect to
     * @return the JSON contents of the URL
     * @throws Exception
     */
    private static String readUrl(String urlString) throws IOException {
        BufferedReader reader = null;
        
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.addRequestProperty("Accept", "application/json");
            
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            
            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

}
