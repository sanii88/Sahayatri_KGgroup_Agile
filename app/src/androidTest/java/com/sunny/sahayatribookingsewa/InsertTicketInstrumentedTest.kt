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

class InsertTicketInstrumentedTest {
    @get : Rule
    val testRule = ActivityScenarioRule(BookingTicketActivity::class.java)

    @Test
    fun checkAddTicket(){
        ServiceBuilder.token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJZb3VySWQiOiI2MWY5NTgzMmIxODE3NmFkYzE2MTIzZDkiLCJpYXQiOjE2NDQwNzY5OTd9.uXGN0VBpycYTrFZVzei7-t8KTYXzgSL4lTtUVpv0IRc"

        Espresso.onView(ViewMatchers.withId(R.id.tvTicketRoute))
            .perform(ViewActions.typeText("Kathmandu-Pokhara"))

        Espresso.closeSoftKeyboard()
        Thread.sleep(500)

        Espresso.onView(ViewMatchers.withId(R.id.tvTicketDate))
            .perform(ViewActions.typeText("5/2/2022"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.tvTicketVehicleType))
            .perform(ViewActions.typeText("Bus"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.tvTicketSeat))
            .perform(ViewActions.typeText("2"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.tvTicketBoardingPoint))
            .perform(ViewActions.typeText("Buspark"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.tvTicketBoardingPerson))
            .perform(ViewActions.typeText("sunny"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.tvTicketContact))
            .perform(ViewActions.typeText("12345"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.btnConfirmBooking))
            .perform(ViewActions.click())

        Thread.sleep(2000)
    }
}