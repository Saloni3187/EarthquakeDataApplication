package com.earthquakeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saloni on 14/10/16.
 */
public class EarthquakeData {
        @SerializedName("count")
        @Expose
        private String count;
        @SerializedName("earthquakes")
        @Expose
        private List<com.earthquakeapp.model.EarthquakesList> earthquakes = new ArrayList<com.earthquakeapp.model.EarthquakesList>();

        /**
         *
         * @return
         * The count
         */
        public String getCount() {
            return count;
        }

        /**
         *
         * @param count
         * The count
         */
        public void setCount(String count) {
            this.count = count;
        }

        /**
         *
         * @return
         * The earthquakes
         */
        public List<com.earthquakeapp.model.EarthquakesList> getEarthquakes() {
            return earthquakes;
        }

        /**
         *
         * @param earthquakes
         * The earthquakes
         */
        public void setEarthquakes(List<com.earthquakeapp.model.EarthquakesList> earthquakes) {
            this.earthquakes = earthquakes;
        }

    }

