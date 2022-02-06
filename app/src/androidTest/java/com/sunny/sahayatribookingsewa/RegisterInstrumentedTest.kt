package com.sunny.sahayatribookingsewa

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class RegisterInstrumentedTest {

    @get : Rule
    val testRule = ActivityScenarioRule(RegisterActivity::class.java)

    @Test
    fun checkSignup() {
        Espresso.onView(ViewMatchers.withId(R.id.etUsername))
            .perform(ViewActions.typeText("Sunny Gurung"))

        Thread.sleep(500)

        Espresso.onView(ViewMatchers.withId(R.id.etPhone))
            .perform(ViewActions.typeText("9843587610"))

        Thread.sleep(500)

        Espresso.onView(ViewMatchers.withId(R.id.etEmail))
            .perform(ViewActions.typeText("sunny@gmail.com"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.etAddress))
            .perform(ViewActions.typeText("Kathmandu"))

        Thread.sleep(500)

        Espresso.onView(ViewMatchers.withId(R.id.etPassword))
            .perform(ViewActions.typeText("sunny123"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.etConfirmPassword))
            .perform(ViewActions.typeText("sunny123"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.btnAddUser))
            .perform(ViewActions.click())

        Thread.sleep(3000)

    }

}