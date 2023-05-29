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
        onView(withId(R.id.button_no_2)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("12")))
        onView(withId(R.id.button_no_3)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("123")))
        onView(withId(R.id.button_no_4)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("1,234")))
        onView(withId(R.id.button_no_5)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("12,345")))
        onView(withId(R.id.button_no_6)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("123,456")))
        onView(withId(R.id.button_no_7)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("1,234,567")))
        onView(withId(R.id.button_no_8)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("12,345,678")))
        onView(withId(R.id.button_no_9)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("123,456,789")))
        onView(withId(R.id.button_no_0)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("1,234,567,890")))
    }

    @Test
    fun addOperation() {
        onView(withId(R.id.button_no_1)).perform(click())
        onView(withId(R.id.button_no_1)).perform(click())
        onView(withId(R.id.button_no_1)).perform(click())
        onView(withId(R.id.button_no_1)).perform(click())
        onView(withId(R.id.button_plus)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("1,111+")))
        onView(withId(R.id.button_no_2)).perform(click())
        onView(withId(R.id.button_no_2)).perform(click())
        onView(withId(R.id.button_no_2)).perform(click())
        onView(withId(R.id.button_no_2)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("1,111+2,222")))
        onView(withId(R.id.button_minus)).perform(click())
        onView(withId(R.id.button_no_3)).perform(click())
        onView(withId(R.id.button_no_3)).perform(click())
        onView(withId(R.id.button_no_3)).perform(click())
        onView(withId(R.id.button_no_3)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("1,111+2,222-3,333")))
        onView(withId(R.id.button_multiply)).perform(click())
        onView(withId(R.id.button_no_4)).perform(click())
        onView(withId(R.id.button_no_4)).perform(click())
        onView(withId(R.id.button_no_4)).perform(click())
        onView(withId(R.id.button_no_4)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("1,111+2,222-3,333×4,444")))
        onView(withId(R.id.button_divide)).perform(click())
        onView(withId(R.id.button_no_5)).perform(click())
        onView(withId(R.id.button_no_5)).perform(click())
        onView(withId(R.id.button_no_5)).perform(click())
        onView(withId(R.id.button_no_5)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("1,111+2,222-3,333×4,444÷5,555")))
        onView(withId(R.id.button_modular)).perform(click())
        onView(withId(R.id.button_no_6)).perform(click())
        onView(withId(R.id.button_no_6)).perform(click())
        onView(withId(R.id.button_no_6)).perform(click())
        onView(withId(R.id.button_no_6)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("1,111+2,222-3,333×4,444÷5,555%6,666")))
        onView(withId(R.id.button_plus)).perform(click())
        onView(withId(R.id.button_left_bracket)).perform(click())
        onView(withId(R.id.button_no_7)).perform(click())
        onView(withId(R.id.button_no_7)).perform(click())
        onView(withId(R.id.button_no_7)).perform(click())
        onView(withId(R.id.button_no_7)).perform(click())
        onView(withId(R.id.button_right_bracket)).perform(click())
        onView(withId(R.id.tv_input)).check(matches(withText("1,111+2,222-3,333×4,444÷5,555%6,666+(7,777)")))
    }
}