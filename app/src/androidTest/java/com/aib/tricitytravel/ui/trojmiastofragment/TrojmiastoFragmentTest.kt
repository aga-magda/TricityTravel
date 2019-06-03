package com.aib.tricitytravel.ui.trojmiastofragment

import android.os.SystemClock
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.aib.tricitytravel.MainActivity
import com.aib.tricitytravel.R
import org.junit.Rule
import org.junit.Test

class TrojmiastoFragmentTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun addNewTagInSettings() {
        onView(withId(R.id.trojmiastoFragment))
            .perform(click())

        try {
            Espresso.onView(ViewMatchers.withId(R.id.settings)).perform(ViewActions.click())
        } catch (nmv: NoMatchingViewException) {
            Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
            Espresso.onView(ViewMatchers.withText(R.string.settings)).perform(ViewActions.click())
        }

        onView(withId(R.id.editKeywordsButton))
            .perform(click())

        onView(withId(R.id.addKeywordEditText))
            .perform(typeText("SKM"), pressImeActionButton())
    }

    @Test
    fun scrollDownAndChangeFilter() {
        onView(withId(R.id.trojmiastoFragment))
            .perform(click())

        SystemClock.sleep(5000)

        onView(withId(R.id.trojmiastoRecyclerView))
            .perform(RecyclerViewActions.scrollToPosition<TrojmiastoRecyclerAdapter.TrojmiastoViewHolder>(20))

        onView(withId(R.id.filter))
            .perform(click())

        onView(withId(R.id.filter))
            .perform(click())
    }
}