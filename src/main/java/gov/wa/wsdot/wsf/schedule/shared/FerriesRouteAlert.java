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

/**
 * Model object to hold alerts associated with the route.
 */
public class FerriesRouteAlert {
    private int BulletinID;
    private String PublishDate;
    private String AlertDescription;
    private String AlertFullTitle;
    private String AlertFullText;
    
    /**
     * Constructor
     */
    public FerriesRouteAlert() {
    }

    /**
     * Constructor
     * 
     * @param BulletinID  unique identifier for the alert
     * @param PublishDate  the date the alert was published
     * @param AlertDescription  the alert text, tailored as a brief route announcement
     * @param AlertFullTitle  the title of the alert
     * @param AlertFullText  the full text of the alert
     */
    public FerriesRouteAlert(int BulletinID, String PublishDate,
            String AlertDescription, String AlertFullTitle, String AlertFullText) {

        this.BulletinID = BulletinID;
        this.PublishDate = PublishDate;
        this.AlertDescription = AlertDescription;
        this.AlertFullTitle = AlertFullTitle;
        this.AlertFullText = AlertFullText;
    }
    
    public int getBulletinID() {
        return BulletinID;
    }
    
    public void setBulletinID(int BulletinID) {
        this.BulletinID = BulletinID;
    }
    
    public String getPublishDate() {
        return PublishDate;
    }
    
    public void setPublishDate(String PublishDate) {
        this.PublishDate = PublishDate;
    }
    
    public String getAlertDescription() {
        return AlertDescription;
    }
    
    public void setAlertDescription(String AlertDescription) {
        this.AlertDescription = AlertDescription;
    }
    
    public String getAlertFullTitle() {
        return AlertFullTitle;
    }
    
    public void setAlertFullTitle(String AlertFullTitle) {
        this.AlertFullTitle = AlertFullTitle;
    }
    
    public String getAlertFullText() {
        return AlertFullText;
    }
    
    public void setAlertFullText(String AlertFullText) {
        this.AlertFullText = AlertFullText;
    }
    
}
