package com.sunny.sahayatribookingsewa

import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class ViewUserInstrumentedTest {

    @get : Rule
    val testRule = ActivityScenarioRule(ShowUser::class.java)

    @Test
    fun checkViewUser(){


        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewUser))

        Thread.sleep(2500)

    }
}