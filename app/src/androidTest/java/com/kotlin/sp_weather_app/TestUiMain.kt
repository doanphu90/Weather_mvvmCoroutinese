package com.kotlin.spweather_app

import android.content.res.Resources
import android.view.KeyEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kotlin.sp_weather_app.R
import com.kotlin.sp_weather_app.view.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestUiMain {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(
        MainActivity::class.java
    )

    @Test
    fun testShowResultWhenInputKey() {
        onView(ViewMatchers.withId(R.id.tv_titleHome))
            .check(ViewAssertions.matches(ViewMatchers.withText("Home")))
        //is RecycleView have screen show
        onView(ViewMatchers.withId(R.id.re_View)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.sv_Search))
            .perform(SearchViewActionExtension.typeText("sin"), closeSoftKeyboard())
        onView(withId(R.id.sv_Search)).perform(click())
        onView(withId(R.id.sv_Search))
            .perform(SearchViewActionExtension.submitText("sin"), closeSoftKeyboard())
        onView(withId(R.id.sv_Search)).perform(click())

        onView(withId(R.id.re_View))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click())
            )
    }
}