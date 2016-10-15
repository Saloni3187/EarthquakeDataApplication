package com.earthquakeapp.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.earthquakeapp.adapters.EarthquakeDetailsDataAdapter;
import com.earthquakeapp.globaldata.GlobalData;
import com.earthquakeapp.model.EarthquakesList;

import java.util.ArrayList;

/**This class is used to display all the data of earthquake in tabular view  based upon the region clicked in the
 * previous list displayed
 * Created by Saloni on 14/10/16.
 */
public class EarthquakeDetailsDataActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TextView tv_country;
    private TextView tv_region;
    private ArrayList<EarthquakesList> earthquakeList;
    private String country;
    private String region;
    private  RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_details_data);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        tv_country = (TextView) findViewById(R.id.tv_country);
        tv_region = (TextView) findViewById(R.id.tv_region);

        getDetailedEarthquakeData();
    }

    /**
     * This method is used to get data required to be displayed from different sources.
     */
    private void getDetailedEarthquakeData() {
        GlobalData data = (GlobalData) getApplicationContext();
        earthquakeList = data.getEarthquakeList();
        region = getIntent().getStringExtra("Region_name");
        country = getIntent().getStringExtra("Country_name");
        setDataInView();
    }

    /**
     * This method is used to set all the data in the appropriate views
     */
    private void setDataInView(){
        EarthquakeDetailsDataAdapter mAdapter;
        tv_country.setText(tv_country.getText()+" "+country);
        tv_region.setText(tv_region.getText()+" "+region);

        mAdapter = new EarthquakeDetailsDataAdapter(earthquakeList);
        //setting values in recycler view i.e data in tabular form.
        mRecyclerView.setAdapter(mAdapter);
    }
}
