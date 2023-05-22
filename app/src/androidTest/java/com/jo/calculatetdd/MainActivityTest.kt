package com.jo.calculatetdd

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @JvmField
    @Rule
    var activityScenarioRule = ActivityScenarioRule(
        MainActivity::class.java
    )
    /**
     * 1234567890 을 차례대로 입력할 것이다.
     * 이 때 text 는 1, 12, 123, 1,234 와 같이
     * 콤마를 포함해서 정확히 떠야만 한다.
     */
    @Test
    fun addNumberSequence() {
        onView(withId(R.id.button_no_1)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("1")))
    }
}