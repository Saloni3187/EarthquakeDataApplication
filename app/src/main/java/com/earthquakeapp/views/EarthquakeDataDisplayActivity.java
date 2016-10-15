package com.earthquakeapp.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import com.earthquakeapp.globaldata.GlobalData;
import com.earthquakeapp.interfaces.EarthquakeDataServiceInterface;
import com.earthquakeapp.model.EarthquakeData;
import com.earthquakeapp.model.EarthquakesList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**This is the first activity of the application,
 * this class is used to get all the data from server, parsing the data retrieved,
 * setting the parsed data in different views and passing the required data to next class as required
 * Created by Saloni on 14/10/2016.
 */
public class EarthquakeDataDisplayActivity extends AppCompatActivity {

    private String selectedCountry;
    private HashSet<String> regions;
    private List<EarthquakesList> earthquakeList;
    private AutoCompleteTextView listOfCountries ;
    private ListView lv_regions;
    private List<String> regions_list;
    private TextView tv_no_data;
    private Boolean isCountryFieldEmpty = false;
    private String countries[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_data_display);
        listOfCountries = (AutoCompleteTextView) findViewById(R.id.name_of_countries);
        lv_regions = (ListView) findViewById(R.id.region_list);
        tv_no_data = (TextView) findViewById(R.id.tv_no_data);

        countries = getResources().getStringArray(R.array.countries);
        regions = new HashSet<>();
        setCountriesList();
    }

    /**
     * This method is used to set properties to drop down view
     * e.g It will clear all the data when text view is empty.
     * Also it will call getDataFromServer() to retrieve data from server when item is clicked from drop down.
     */
    private void setCountriesList() {
        listOfCountries.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countries));

        listOfCountries.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listOfCountries.showDropDown();
                if (listOfCountries.getText().toString().equals("")) {
                    isCountryFieldEmpty = true;
                    regions.clear();
                    regions_list.clear();
                    setDataInRegionList();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        listOfCountries.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                selectedCountry = listOfCountries.getText().toString();
                listOfCountries.dismissDropDown();
                getDataFromServer();
            }

        });

        listOfCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCountry = adapterView.getItemAtPosition(i).toString();
                getDataFromServer();
            }
        });

        listOfCountries.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                listOfCountries.showDropDown();
            }
        });

    }

    /**
     * Method is used to retrieve data from server and parse data by calling parseServerData(earthquakeList)
     * earthquake list having all the data from server
     */
    private void getDataFromServer() {
        EarthquakeDataServiceInterface.Factory.getInstance().getEarthquakeData().enqueue(new Callback<EarthquakeData>() {
            @Override
                public void onResponse(Call<EarthquakeData> call, final Response<EarthquakeData> response) {
                    earthquakeList = response.body().getEarthquakes();
                    parseServerData(earthquakeList ,selectedCountry.toLowerCase());
                }
                @Override
                public void onFailure(Call<EarthquakeData> call, Throwable t) {
                    tv_no_data.setVisibility(View.VISIBLE);
                    tv_no_data.setText("ERROR IN URL");
                }
            });
    }


    /**
     * Method will parse all the data and call setDataInRegionList() to set data in views
     * Also check if region list is empty, it will set the flag to false
     * @param earthquakes
     */
    private ArrayList<EarthquakesList> parseServerData(List<EarthquakesList> earthquakes , String selectedCountry){
        ArrayList results = new ArrayList<EarthquakesList>();
        if(earthquakes.size()!=0) {
            for (EarthquakesList data : earthquakes) {
                if(!selectedCountry.equals("")){
                    if (data.getRegion().toLowerCase().contains(selectedCountry)) {
                        regions.add(data.getRegion());
                        results.add(data);
                    }
                }
            }
        }
        if(regions.size()==0){
            isCountryFieldEmpty = false;
        }

        setDataInRegionList();
        return results;
    }

    /**
     * Method will set all the parsed data
     * to list view, If regions are available.
     * If not It will not set the data in list and show a text accordingly.
     * If the flag is true, i.e if no country is selected, It will show no list but a blank page.
     * Also the method is passing the data to another class when clicked on one of the list items.
     */
    private void setDataInRegionList() {
        regions_list = new ArrayList<String>(regions);
        //if region list is empty ,hide list view
        if (regions_list.size() == 0) {
            lv_regions.setAdapter(new ArrayAdapter<String>(
                    EarthquakeDataDisplayActivity.this, R.layout.region_list_properties, R.id.list_content, regions_list));
            lv_regions.setVisibility(View.GONE);
            //if no region retrieved from server based on country, show text view with msg,else if edit text is blank show nothing
            if(!isCountryFieldEmpty && (!listOfCountries.getText().toString().equals(""))) {
                tv_no_data.setVisibility(View.VISIBLE);
                tv_no_data.setText(getResources().getString(R.string._txt_no_data_found));
            }

            else
                tv_no_data.setVisibility(View.GONE);
        }
        //if regions are available for selected country, set the list in list view adapter
        else {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            lv_regions.setVisibility(View.VISIBLE);
            tv_no_data.setText(getResources().getString(R.string._txt_list_title));
            tv_no_data.setVisibility(View.VISIBLE);
            lv_regions.setAdapter(new ArrayAdapter<String>(
                    EarthquakeDataDisplayActivity.this, R.layout.region_list_properties, R.id.list_content, regions_list));
            lv_regions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String region_name = adapterView.getItemAtPosition(i).toString();
                    GlobalData data = (GlobalData) getApplicationContext();

                    //setting the name of next activity
                    Intent intent = new Intent(EarthquakeDataDisplayActivity.this, EarthquakeDetailsDataActivity.class);
                    data.setEarthquakeList(parseServerData(earthquakeList, region_name));
                    //setting data to be passed in next activity
                    intent.putExtra("Region_name", region_name);
                    intent.putExtra("Country_name", selectedCountry);
                    //starting the activity
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
    // DO NOTHING...
    }
}