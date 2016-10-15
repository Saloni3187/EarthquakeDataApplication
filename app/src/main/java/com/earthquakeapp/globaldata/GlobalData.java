package com.earthquakeapp.globaldata;

import android.app.Application;
import com.earthquakeapp.model.EarthquakesList;
import java.util.ArrayList;

/**This class used to set and get global data values needs to be used throughout the application.
 * Created by Saloni on 14/10/16.
 */
public class GlobalData extends Application {
    private  ArrayList<EarthquakesList> earthquakeList;

    public ArrayList<EarthquakesList> getEarthquakeList() {
        return earthquakeList;
    }

    public void setEarthquakeList(ArrayList<EarthquakesList> earthquakeList) {
        this.earthquakeList = earthquakeList;
    }

}
