package com.sunny.sahayatribookingsewa

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class ViewTicketInstrumentedTest {
    @get : Rule
    val testRule = ActivityScenarioRule(ViewTicketActivity::class.java)

    @Test
    fun checkViewTicket(){


        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewTicket))

        Thread.sleep(2500)

    }
}