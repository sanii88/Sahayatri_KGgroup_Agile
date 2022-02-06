package com.sunny.sahayatribookingsewa

import android.widget.Button
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.sunny.sahayatribookingsewa.api.ServiceBuilder
import org.junit.Rule
import org.junit.Test

class InsertHiringInstrumentedTest {

    @get : Rule
    val testRule = ActivityScenarioRule(HiringTicketActivity::class.java)

    @Test
    fun checkAddTicket(){
        ServiceBuilder.token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJZb3VySWQiOiI2MWY5NTgzMmIxODE3NmFkYzE2MTIzZDkiLCJpYXQiOjE2NDQwNzY5OTd9.uXGN0VBpycYTrFZVzei7-t8KTYXzgSL4lTtUVpv0IRc"

        Espresso.onView(ViewMatchers.withId(R.id.tvHireVehicleType))
            .perform(ViewActions.typeText("Bus"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.tvHireDays))
            .perform(ViewActions.typeText("2"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.tvHireDate))
            .perform(ViewActions.typeText("5/2/2022"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.tvHireContact))
            .perform(ViewActions.typeText("12345"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.btnConfirmHiring))
            .perform(ViewActions.click())

        Thread.sleep(2000)
    }

}