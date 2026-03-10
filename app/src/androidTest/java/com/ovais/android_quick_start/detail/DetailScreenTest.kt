package com.ovais.android_quick_start.detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ovais.android_quick_start.features.detail.presentation.DetailScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun detailScreen_displaysAllData() {
        val identifier = "12345"
        val model = "Pixel 8"
        val version = "Android 14"
        val name = "Syed Ovais Akhtar"

        // Set content
        composeTestRule.setContent {
            DetailScreen(
                identifier = identifier,
                model = model,
                version = version,
                name = name
            )
        }

        // Check main screen
        composeTestRule.onNodeWithTag("DetailScreen").assertIsDisplayed()

        // Check Cover Image
        composeTestRule.onNodeWithTag("CoverImage").assertIsDisplayed()

        // Check Avatar
        composeTestRule.onNodeWithTag("AvatarView").assertIsDisplayed()

        // Check Card
        composeTestRule.onNodeWithTag("DetailCard").assertIsDisplayed()

        // Check Name row
        composeTestRule.onNodeWithTag("NameRow").assertIsDisplayed()
        composeTestRule.onNodeWithTag("NameLabel").assertTextEquals("Name")
        composeTestRule.onNodeWithTag("NameValue").assertTextEquals(name)

        // Check Model row
        composeTestRule.onNodeWithTag("ModelRow").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ModelLabel").assertTextEquals("Model")
        composeTestRule.onNodeWithTag("ModelValue").assertTextEquals(model)

        // Check Identifier row
        composeTestRule.onNodeWithTag("IdentifierRow").assertIsDisplayed()
        composeTestRule.onNodeWithTag("IdentifierLabel").assertTextEquals("Identifier")
        composeTestRule.onNodeWithTag("IdentifierValue").assertTextEquals(identifier)

        // Check Version row
        composeTestRule.onNodeWithTag("VersionRow").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Android VersionLabel").assertTextEquals("Android Version")
        composeTestRule.onNodeWithTag("Android VersionValue").assertTextEquals(version)
    }
}