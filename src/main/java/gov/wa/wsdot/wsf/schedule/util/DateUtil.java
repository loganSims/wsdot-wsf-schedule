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

package gov.wa.wsdot.wsf.schedule.util;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * A class containing datetime specific utility methods. 
 */
public class DateUtil {

    /**
     * Increases a date by adding days to it.
     * 
     * @param date  the date to increment
     * @param days  how many days to add
     * @return a new date incremented by a number of days
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        
        return cal.getTime();
    }
    
    /**
     * Serializes a date into a .NET JSON format.
     * <p>
     * Responses from the .NET service returns JSON formatted dates
     * in the form of {@code /Date(1437776253853-0700)/}.
     * <p>
     * The WSDOT mobile apps are already setup to parse this format.
     * If we change it in the schedule feed we will have to modify
     * the code in the mobile apps and redeploy them. For now, we'll
     * leave the existing format in place.
     * 
     * @param date  the date to serialize
     * @return the date formatted as a .NET JSON timestamp
     * @see <a href="http://stackoverflow.com/a/15365257">http://stackoverflow.com/a/15365257</a>
     */
    public static String serialize(Date date) {
        DecimalFormat formatter = new DecimalFormat("#00.###");
        String minusSign = "-";
        String plusSign = "+";
        int zoneOffsetMillisecond = TimeZone.getDefault().getOffset(date.getTime());
        String sign = plusSign;
        
        if (zoneOffsetMillisecond < 0) { // negative offset
            sign = minusSign;
            zoneOffsetMillisecond *= -1;
        }
        
        int minute = zoneOffsetMillisecond % (60 * 60 * 1000); // Hour in milliseconds
        int hour = (zoneOffsetMillisecond / 1000 / 60 / 60);
        
        String serializedDate = "/Date(" + date.getTime() + sign
                + formatter.format(hour) + formatter.format(minute) + ")/"; 
        
        return serializedDate;
    }
}
