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

import java.util.List;

public class RouteDetailsResponse {
    private int RouteID;
    private String RouteAbbrev;
    private String Description;
    private int RegionID;
    private int VesselWatchID;
    private boolean ReservationFlag;
    private boolean InternationalFlag;
    private boolean PassengerOnlyFlag;
    private String CrossingTime;
    private String AdaNotes;
    private String GeneralRouteNotes;
    private String SeasonalRouteNotes;
    private List<Alerts> Alerts;

    public int getRouteID() {
        return RouteID;
    }

    public String getRouteAbbrev() {
        return RouteAbbrev;
    }

    public String getDescription() {
        return Description;
    }

    public int getRegionID() {
        return RegionID;
    }

    public int getVesselWatchID() {
        return VesselWatchID;
    }

    public boolean isReservationFlag() {
        return ReservationFlag;
    }

    public boolean isInternationalFlag() {
        return InternationalFlag;
    }

    public boolean isPassengerOnlyFlag() {
        return PassengerOnlyFlag;
    }

    public String getCrossingTime() {
        return CrossingTime;
    }

    public String getAdaNotes() {
        return AdaNotes;
    }

    public String getGeneralRouteNotes() {
        return GeneralRouteNotes;
    }

    public String getSeasonalRouteNotes() {
        return SeasonalRouteNotes;
    }

    public List<Alerts> getAlerts() {
        return Alerts;
    }
    
    public class Alerts {
        private int BulletinID;
        private boolean BulletinFlag;
        private boolean CommunicationFlag;
        private String PublishDate;
        private String AlertDescription;
        private String DisruptionDescription;
        private String AlertFullTitle;
        private String AlertFullText;
        private String IVRText;
        
        public int getBulletinID() {
            return BulletinID;
        }

        public boolean isBulletinFlag() {
            return BulletinFlag;
        }

        public boolean isCommunicationFlag() {
            return CommunicationFlag;
        }

        public String getPublishDate() {
            return PublishDate;
        }
        
        public String getAlertDescription() {
            return AlertDescription;
        }

        public String getDisruptionDescription() {
            return DisruptionDescription;
        }

        public String getAlertFullTitle() {
            return AlertFullTitle;
        }

        public String getAlertFullText() {
            return AlertFullText;
        }

        public String getIVRText() {
            return IVRText;
        }
    }
    
}
