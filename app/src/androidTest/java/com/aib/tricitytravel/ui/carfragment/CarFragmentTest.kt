package com.aib.tricitytravel.ui.carfragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.aib.tricitytravel.MainActivity
import com.aib.tricitytravel.R
import org.junit.Rule
import org.junit.Test

class CarFragmentTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun typeStartEndLocationsAndClickSearch_routeShowed() {
        onView(withId(R.id.carFragment))
            .perform(click())

        onView(withId(R.id.startLocationTextView))
            .perform(replaceText("Gdańsk"))

        onView(withId(R.id.endLocationTextView))
            .perform(replaceText("Sopot"))

        onView(withId(R.id.timeOfTravelButton))
            .perform(click())

        onView(withId(R.id.timeOfTravelCardView))
            .check(matches(isDisplayed()))
    }
}