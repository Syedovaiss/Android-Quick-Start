package com.ovais.android_quick_start.home

import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ovais.android_quick_start.features.home.presentation.HomeSuccessView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeSuccessViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeSuccessView_displaysAllInfo_andContinueWorks() {
        // Arrange: define test data
        val testModel = "Pixel 8"
        val testIdentifier = "FAKE123"
        val testVersion = "13"

        var clicked = false
        var clickedModel = ""
        var clickedId = ""
        var clickedVersion = ""

        composeTestRule.setContent {
            HomeSuccessView(
                model = testModel,
                identifier = testIdentifier,
                androidVersion = testVersion,
                onNextClick = { model, id, version ->
                    clicked = true
                    clickedModel = model
                    clickedId = id
                    clickedVersion = version
                }
            )
        }

        // Assert: InfoRows display correct text
        composeTestRule.onNodeWithTag("info_row_📱 Model").assertIsDisplayed()
        composeTestRule.onNodeWithTag("info_row_📱 Model").onChildren()
            .assertAny(hasText(testModel))

        composeTestRule.onNodeWithTag("info_row_🆔 Identifier").assertIsDisplayed()
        composeTestRule.onNodeWithTag("info_row_🆔 Identifier").onChildren()
            .assertAny(hasText(testIdentifier))

        composeTestRule.onNodeWithTag("info_row_🤖 Android Version").assertIsDisplayed()
        composeTestRule.onNodeWithTag("info_row_🤖 Android Version").onChildren()
            .assertAny(hasText(testVersion))

        // Assert: Continue button is displayed
        composeTestRule.onNodeWithTag("continue_button").assertIsDisplayed()

        // Act: click the Continue button
        composeTestRule.onNodeWithTag("continue_button").performClick()

        // Assert: callback triggered with correct values
        assert(clicked)
        assert(clickedModel == testModel)
        assert(clickedId == testIdentifier)
        assert(clickedVersion == testVersion)
    }
}