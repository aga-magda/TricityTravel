package com.aib.tricitytravel.ui.publictransportfragment

import android.os.SystemClock
import androidx.recyclerview.widget.RecyclerView
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.aib.tricitytravel.MainActivity
import com.aib.tricitytravel.R
import com.aib.tricitytravel.util.clickChildViewWithId
import org.junit.Rule
import org.junit.Test

class PublicTransportFragmentTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun goToSettingsAndAddNewStop() {
        try {
            onView(withId(R.id.settings)).perform(ViewActions.click())
        } catch (nmv: NoMatchingViewException) {
            Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
            onView(ViewMatchers.withText(R.string.settings)).perform(ViewActions.click())
        }

        onView(withId(R.id.editStopsButton))
            .perform(click())

        SystemClock.sleep(25000)

        onView(withId(R.id.selectStopRecyclerView))
            .perform(
                actionOnItemAtPosition<PublicTransportRecyclerAdapter.PublicTransportViewHolder>(
                    0,
                    clickChildViewWithId(R.id.addStopButton)
                )
            )

        onView(withText(R.string.save))
            .perform(click())

        Espresso.pressBack()
    }

    @Test
    fun openExampleStopAndComeBack() {
        onView(withId(R.id.publicTransportFragment))
            .perform(click())

        onView(withId(R.id.publicTransportRecyclerView))
            .perform(
                actionOnItemAtPosition<PublicTransportRecyclerAdapter.PublicTransportViewHolder>(0, click())
            )

        Espresso.pressBack()
    }

    @Test
    fun deleteExampleStopAndComeBack() {
        onView(withId(R.id.publicTransportFragment))
            .perform(click())

        onView(withId(R.id.publicTransportRecyclerView))
            .perform(
                actionOnItemAtPosition<PublicTransportRecyclerAdapter.PublicTransportViewHolder>(0, longClick())
            )

        onView(withText(R.string.yes))
            .perform(click())

        Espresso.pressBack()
    }
}