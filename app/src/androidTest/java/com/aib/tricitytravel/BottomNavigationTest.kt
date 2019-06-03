package com.aib.tricitytravel

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class BottomNavigationTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickMainFragmentNavigationButton_openMainFragment() {
        onView(withId(R.id.mainFragment))
            .perform(click())
            .check(matches(isDisplayed()))
    }

    @Test
    fun clickPublicTransportFragmentNavigationButton_openPublicTransportFragment() {
        onView(withId(R.id.publicTransportFragment))
            .perform(click())
            .check(matches(isDisplayed()))
    }

    @Test
    fun clickCarFragmentNavigationButton_openCarFragment() {
        onView(withId(R.id.carFragment))
            .perform(click())
            .check(matches(isDisplayed()))
    }

    @Test
    fun clickTrojmiastoFragmentNavigationButton_openTrojmiastoFragment() {
        onView(withId(R.id.trojmiastoFragment))
            .perform(click())
            .check(matches(isDisplayed()))
    }
}