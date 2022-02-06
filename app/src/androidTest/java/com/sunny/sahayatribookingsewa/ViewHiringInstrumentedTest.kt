package com.sunny.sahayatribookingsewa

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class ViewHiringInstrumentedTest {

    @get : Rule
    val testRule = ActivityScenarioRule(ViewHiringActivity::class.java)

    @Test
    fun checkViewHiring(){

        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewHiring))

        Thread.sleep(2500)

    }
}