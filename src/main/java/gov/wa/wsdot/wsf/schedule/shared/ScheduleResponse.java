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

/**
 * Model object to hold departure times for a trip date and route. 
 */
public class ScheduleResponse {
    private int ScheduleID;
    private String ScheduleName;
    private int ScheduleSeason;
    private String SchedulePDFUrl;
    private String ScheduleStart;
    private String ScheduleEnd;
    private List<Integer> AllRoutes;
    private List<TerminalCombos> TerminalCombos;
    
    public int getScheduleID() {
        return ScheduleID;
    }

    public String getScheduleName() {
        return ScheduleName;
    }

    public int getScheduleSeason() {
        return ScheduleSeason;
    }

    public String getSchedulePDFUrl() {
        return SchedulePDFUrl;
    }

    public String getScheduleStart() {
        return ScheduleStart;
    }

    public String getScheduleEnd() {
        return ScheduleEnd;
    }

    public List<Integer> getAllRoutes() {
        return AllRoutes;
    }

    public List<TerminalCombos> getTerminalCombos() {
        return TerminalCombos;
    }
    
    /**
     * Model object to hold grouping of departure and arrival terminal pairs.
     */
    public class TerminalCombos {
        private int DepartingTerminalID;
        private String DepartingTerminalName;
        private int ArrivingTerminalID;
        private String ArrivingTerminalName;
        private String SailingNotes;
        private List<String> Annotations;
        private List<Times> Times;
        
        public int getDepartingTerminalID() {
            return DepartingTerminalID;
        }

        public String getDepartingTerminalName() {
            return DepartingTerminalName;
        }

        public int getArrivingTerminalID() {
            return ArrivingTerminalID;
        }

        public String getArrivingTerminalName() {
            return ArrivingTerminalName;
        }

        public String getSailingNotes() {
            return SailingNotes;
        }

        public List<String> getAnnotations() {
            return Annotations;
        }

        public List<Times> getTimes() {
            return Times;
        }
        
        /**
         * Model object to hold scheduled departure details, including departure times.
         */
        public class Times {
            private String DepartingTime;
            private String ArrivingTime;
            private int LoadingRule;
            private int VesselID;
            private String VesselName;
            private boolean VesselHandicapAccessible;
            private int VesselPositionNum;
            private List<Integer> Routes;
            private List<Integer> AnnotationIndexes;
            
            public String getDepartingTime() {
                return DepartingTime;
            }

            public String getArrivingTime() {
                return ArrivingTime;
            }

            public int getLoadingRule() {
                return LoadingRule;
            }

            public int getVesselID() {
                return VesselID;
            }

            public String getVesselName() {
                return VesselName;
            }

            public boolean isVesselHandicapAccessible() {
                return VesselHandicapAccessible;
            }

            public int getVesselPositionNum() {
                return VesselPositionNum;
            }

            public List<Integer> getRoutes() {
                return Routes;
            }

            public List<Integer> getAnnotationIndexes() {
                return AnnotationIndexes;
            }

        }

    }

}
