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

package gov.wa.wsdot.wsf.schedule.shared;

import java.util.ArrayList;
import java.util.List;

/**
 * Model object to hold detailed information pertaining to routes.
 */
public class FerriesRoute {
    private int RouteID;
    private String Description;
    private String CacheDate;
    private String CrossingTime;
    private List<Dates> Date = new ArrayList<Dates>();
    private List<FerriesRouteAlert> RouteAlert = new ArrayList<FerriesRouteAlert>();
    
    /**
     * Constructor
     */
    public FerriesRoute() {
    }

    /**
     * Constructor
     * 
     * @param RouteId  unique identifier for a route
     * @param Description  the full name of the route
     * @param CacheDate  the date the service data was last changed
     * @param CrossingTime  estimated crossing time (in minutes) associated with the route
     */
    public FerriesRoute(int RouteId, String Description, String CacheDate, String CrossingTime) {
        this.RouteID = RouteId;
        this.Description = Description;
        this.CacheDate = CacheDate;
        this.CrossingTime = CrossingTime;
    }
    
    public int getRouteID() {
        return RouteID;
    }
    
    public void setRouteID(int RouteID) {
        this.RouteID = RouteID;
    }
    
    public String getDescription() {
        return Description;
    }
    
    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    public String getCacheDate() {
        return CacheDate;
    }
    
    public void setCacheDate(String CacheDate) {
        this.CacheDate = CacheDate;
    }
    
    public String getCrossingTime() {
        return CrossingTime;
    }

    public void setCrossingTime(String crossingTime) {
        CrossingTime = crossingTime;
    }

    public List<Dates> getDate() {
        return Date;
    }
    
    public void setDate(Dates Date) {
        this.Date.add(Date);
    }
    
    public List<FerriesRouteAlert> getRouteAlert() {
        return RouteAlert;
    }
    
    public void setRouteAlert(FerriesRouteAlert RouteAlert) {
        this.RouteAlert.add(RouteAlert);
    }
    
}
