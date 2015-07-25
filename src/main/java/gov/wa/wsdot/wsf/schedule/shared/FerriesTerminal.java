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
 * Model object to hold a grouping of departure and arrival terminal pairs. 
 */
public class FerriesTerminal {
    private int DepartingTerminalID;
    private String DepartingTerminalName;
    private int ArrivingTerminalID;
    private String ArrivingTerminalName;
    private List<String> Annotations = new ArrayList<String>();
    private List<FerriesScheduleTimes> Times = new ArrayList<FerriesScheduleTimes>();
    
    /**
     * Constructor
     */
    public FerriesTerminal() {
    }
    
    /**
     * Constructor
     * 
     * @param DepartingTerminalID  unique identifier for the departing terminal
     * @param DepartingTerminalName  the name of the departing terminal
     * @param ArrivingTerminalID  unique identifier for the arriving terminal
     * @param ArrivingTerminalName  the name of the arriving terminal
     */
    public FerriesTerminal(int DepartingTerminalID,
            String DepartingTerminalName, int ArrivingTerminalID,
            String ArrivingTerminalName) {
        
        this.DepartingTerminalID = DepartingTerminalID;
        this.DepartingTerminalName = DepartingTerminalName;
        this.ArrivingTerminalID = ArrivingTerminalID;
        this.ArrivingTerminalName = ArrivingTerminalName;
    }
    
    public int getDepartingTerminalID() {
        return DepartingTerminalID;
    }
    
    public void setDepartingTerminalId(int DepartingTerminalID) {
        this.DepartingTerminalID = DepartingTerminalID;
    }
    
    public String getDepartingTerminalName() {
        return DepartingTerminalName;
    }
    
    public void setDepartingTerminalName(String DepartingTerminalName) {
        this.DepartingTerminalName = DepartingTerminalName;
    }
    
    public int getArrivingTerminalId() {
        return ArrivingTerminalID;
    }
    
    public void setArrivingTerminalId(int ArrivingTerminalID) {
        this.ArrivingTerminalID = ArrivingTerminalID;
    }
    
    public String getArrivingTerminalName() {
        return ArrivingTerminalName;
    }
    
    public void setArrivingTerminalName(String ArrivingTerminalName) {
        this.ArrivingTerminalName = ArrivingTerminalName;
    }
    
    public List<String> getAnnotations() {
        return Annotations;
    }
    
    /**
     * Set annotations for this terminal.
     * 
     * @param Annotations array of annotation strings assigned to one
     * or more items in the Times array
     */
    public void setAnnotations(String Annotations) {
        this.Annotations.add(Annotations);
    }
    
    public List<FerriesScheduleTimes> getTimes() {
        return Times;
    }
    
    /**
     * Set departure details for this terminal.
     * 
     * @param Times  scheduled departure details, including departure times
     */
    public void setTimes(FerriesScheduleTimes Times) {
        this.Times.add(Times);
    }
}
