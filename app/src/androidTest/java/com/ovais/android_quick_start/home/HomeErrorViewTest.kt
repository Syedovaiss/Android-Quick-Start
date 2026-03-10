package com.ovais.android_quick_start.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ovais.android_quick_start.features.home.presentation.HomeErrorView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeErrorViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeErrorView_displaysAllContent_andRetryWorks() {
        var retryClicked = false

        composeTestRule.setContent {
            HomeErrorView(
                title = "Network Error",
                message = "Failed to load data",
                onRetry = { retryClicked = true }
            )
        }

        // Check if all elements are displayed
        composeTestRule.onNodeWithTag("error_screen").assertIsDisplayed()
        composeTestRule.onNodeWithTag("error_card").assertIsDisplayed()
        composeTestRule.onNodeWithTag("error_title")
            .assertIsDisplayed()
            .assertTextEquals("Network Error")
        composeTestRule.onNodeWithTag("error_message")
            .assertIsDisplayed()
            .assertTextEquals("Failed to load data")
        composeTestRule.onNodeWithTag("retry_button").assertIsDisplayed()

        // Perform click on retry
        composeTestRule.onNodeWithTag("retry_button").performClick()

        // Assert that click callback worked
        assert(retryClicked)
    }
}