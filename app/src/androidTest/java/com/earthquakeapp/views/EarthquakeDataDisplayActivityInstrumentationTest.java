package com.earthquakeapp.views;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;

/**
 * Created by Saloni on 15/10/16.
 */
public class EarthquakeDataDisplayActivityInstrumentationTest {

    // Preferred JUnit 4 mechanism of specifying the activity to be launched before each test
    @Rule
    public ActivityTestRule<EarthquakeDataDisplayActivity> activityTestRule =
            new ActivityTestRule<>(EarthquakeDataDisplayActivity.class);

    // Looks for an Autocomplete Text  with id = "R.id.name_of_countries"
    // Types the text "" into the Autocomplete Text and click
    // Verifies the TextView with id = "R.id.tv_no_data" visibility gone.
    @Test
    public void validateCountriesTextWithEmptyData() {
        onView(withId(R.id.name_of_countries)).perform(typeText("")).perform(click());
        onView(withId(R.id.tv_no_data)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    // Looks for an Autocomplete Text  with id = "R.id.name_of_countries"
    // Types the text "" into the Autocomplete Text and click
    // Verifies the TextView with id = "R.id.tv_no_data" visibility gone.
    @Test
    public void validateRegionListWhenDataAvailable() {
        onView(withId(R.id.name_of_countries)).perform(typeText("Japan")).perform(click());
        onData(hasToString(containsString("Japan"))).inAdapterView(withId(R.id.region_list)).atPosition(3);;
        onView(withId(R.id.tv_no_data)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

    }
    // Looks for an Autocomplete Text  with id = "R.id.name_of_countries"
    // Types the text "" into the Autocomplete Text and click
    // if no data available
    // Verifies the TextView with id = "R.id.tv_no_data" has text NO DATA FOUND.
    @Test
    public void validateRegionListWhenDataNotAvailable() {
        onView(withId(R.id.name_of_countries)).perform(typeText("Latin America")).perform(click());
        onView(withId(R.id.tv_no_data)).check(matches(withText("NO DATA FOUND")));


    }

}
