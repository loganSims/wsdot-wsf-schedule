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
 * Model object to hold scheduled departure details, including departure times.
 */
public class FerriesScheduleTimes {
    private String DepartingTime;
    private String ArrivingTime;
    private List<Integer> AnnotationIndexes = new ArrayList<Integer>();
    
    /**
     * Constructor
     */
    public FerriesScheduleTimes() {
    }
    
    /**
     * Constructor
     * 
     * @param DepartingTime  the date and time of the departure
     * @param ArrivingTime  the date and time of the arrival
     */
    public FerriesScheduleTimes(String DepartingTime, String ArrivingTime) {
        this.DepartingTime = DepartingTime;
        this.ArrivingTime = ArrivingTime;
    }
    
    public String getDepartingTime() {
        return DepartingTime;
    }
    
    public void setDepartingTime(String DepartingTime) {
        this.DepartingTime = DepartingTime;
    }
    
    public String getArrivingTime() {
        return ArrivingTime;
    }
    
    public void setArrivingTime(String ArrivingTime) {
        this.ArrivingTime = ArrivingTime;
    }
    
    public List<Integer> getAnnotationIndexes() {
        return AnnotationIndexes;
    }
    
    /**
     * Reference elements in the Annotations array that apply to this departure.
     * 
     * @param AnnotationIndexes  array of index integers indicating the elements
     * in the Annotations array that apply to this departure.
     */
    public void setAnnotationIndexes(Integer AnnotationIndexes) {
        this.AnnotationIndexes.add(AnnotationIndexes);
    }  

}
