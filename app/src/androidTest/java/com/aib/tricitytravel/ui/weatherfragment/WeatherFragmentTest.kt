package com.aib.tricitytravel.ui.weatherfragment

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.aib.tricitytravel.MainActivity
import com.aib.tricitytravel.R
import org.junit.Rule
import org.junit.Test

class WeatherFragmentTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun goToSettingsSetCityAndBackToMainFragment_cityIsChanged() {
        try {
            onView(withId(R.id.settings)).perform(click())
        } catch (nmv: NoMatchingViewException) {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
            onView(withText(R.string.settings)).perform(click())
        }

        onView(withId(R.id.weatherEditText))
            .perform(replaceText("Gdańsk"))

        Espresso.pressBack()

        onView(withId(R.id.textView2))
            .check(matches(withText("Gdańsk")))
    }
}