package com.geraldin.ceibausers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.geraldin.ceibausers.presentation.MainActivity
import com.geraldin.ceibausers.util.adapters.UserAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class UsersFragmentTest {
    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_selectListItem_isDetailFragmentVisible() {
        onView(withId(R.id.usersRecyclerView)).perform(
            RecyclerViewActions
                .actionOnItemAtPosition<UserAdapter.ViewHolder>(4, ViewActions.click())
        )
    }
}
