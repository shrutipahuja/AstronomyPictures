package com.example.astronomypictures.detail

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.astronomypictures.R
import com.example.astronomypictures.grid.GridActivity
import com.example.astronomypictures.grid.GridAdapter.GridViewHolder
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class DetailFragmentTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<GridActivity> =
        ActivityScenarioRule(GridActivity::class.java)

    val LIST_ITEM_IN_TEST = 4

    /**
     * Get nth child of Images
     */
    fun nthChildOf(parentMatcher: Matcher<View?>, childPosition: Int): Matcher<View?>? {
        return object : TypeSafeMatcher<View?>() {
            override fun describeTo(description: Description) {
                description.appendText("with $childPosition child view of type parentMatcher")
            }

            override fun matchesSafely(view: View?): Boolean {
                if (view != null) {
                    if (view.parent !is ViewGroup) {
                        return parentMatcher.matches(view.parent)
                    }
                }
                val group = view?.parent as ViewGroup
                return parentMatcher.matches(view.getParent()) && view.equals(
                    group.getChildAt(
                        childPosition
                    )
                )
            }
        }
    }

    /**
     * Check if image details are visible on
     * image click
     */
    @Test
    fun test_isImageDetailsVisible() {
        onView(withId(R.id.photos_grid))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<GridViewHolder>(
                    LIST_ITEM_IN_TEST,
                    ViewActions.click()
                )
            )
        onView(nthChildOf(withId(R.id.viewPager), 0)).check(matches(withId(R.id.constraint_layout)))
    }

    /**
     * Check if next image is displayed on
     * swipe of images
     */
    @Test
    fun test_swipeOnImage_isNextImageVisible() {
        onView(withId(R.id.photos_grid))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<GridViewHolder>(
                    LIST_ITEM_IN_TEST,
                    ViewActions.click()
                )
            )
        onView(withId(R.id.viewPager)).check(matches(ViewMatchers.isCompletelyDisplayed()))
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft())
        onView(CoreMatchers.allOf(withId(R.id.title_label), ViewMatchers.isCompletelyDisplayed()))
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeRight())
        onView(CoreMatchers.allOf(withId(R.id.title_label), ViewMatchers.isCompletelyDisplayed()))
    }
}