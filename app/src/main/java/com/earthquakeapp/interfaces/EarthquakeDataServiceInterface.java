package com.earthquakeapp.interfaces;

import com.earthquakeapp.constants.Constants;
import com.earthquakeapp.model.EarthquakeData;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**This class calls retrofit library to get data from given URL
 * Created by Saloni on 14/10/16.
 */
public interface EarthquakeDataServiceInterface {
    @GET(Constants.EARTHQUAKES_DATA)
    Call<EarthquakeData> getEarthquakeData();

    //inner class used to create singleton instance
    class Factory{
        private static EarthquakeDataServiceInterface service;
        public static EarthquakeDataServiceInterface getInstance(){
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).
                                        addConverterFactory(GsonConverterFactory.create()).build();

                service = retrofit.create(EarthquakeDataServiceInterface.class);
                return service;
            }else
                return service;
            }
        }
    }
