package br.com.android.currencyrates

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    @JvmField
    @Rule
    val testRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun initialViewsDisplayedProperly() {
        onView(withId(R.id.usd)).check(matches(isDisplayed()))
        onView(withId(R.id.usd)).check(matches(withText(containsString("USD"))))
        onView(withId(R.id.pln)).check(matches(isDisplayed()))
        onView(withId(R.id.pln)).check(matches(withText(containsString("PLN"))))
    }
}