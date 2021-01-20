package com.example.astronomypictures.grid


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import com.example.astronomypictures.grid.GridAdapter.GridViewHolder
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.astronomypictures.R
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test

class GridActivityTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<GridActivity> =
        ActivityScenarioRule(GridActivity::class.java)

    val LIST_ITEM_IN_TEST = 4

    /**
     * Check if photo grid is visible
     */
    @Test
    fun test_isGridVisible_onAppLaunch() {
        onView(withId(R.id.photos_grid)).check(matches(isCompletelyDisplayed()))
    }

    /**
     * Check if detail fragment is visible
     * on image click
     */
    @Test
    fun test_selectGridItem_isDetailFragmentVisible() {
        onView(withId(R.id.photos_grid))
            .perform(actionOnItemAtPosition<GridViewHolder>(LIST_ITEM_IN_TEST, click()))
        onView(allOf(withId(R.id.title_label), isCompletelyDisplayed()))
    }

    /**
     * Check if photo grid is displayed
     * on back button press
     */
    @Test
    fun test_backNavigation_toPhotoGridActivity() {
        onView(withId(R.id.photos_grid))
            .perform(actionOnItemAtPosition<GridViewHolder>(LIST_ITEM_IN_TEST, click()))
        onView(allOf(withId(R.id.title_label), isCompletelyDisplayed()))
        pressBack()
        onView(withId(R.id.photos_grid)).check(matches(isCompletelyDisplayed()))
    }
}