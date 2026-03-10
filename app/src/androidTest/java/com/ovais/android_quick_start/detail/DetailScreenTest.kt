package com.ovais.android_quick_start.detail
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ovais.android_quick_start.features.detail.presentation.DetailScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun detailScreen_displaysAllInfo() {
        val name = "Syed Ovais Akhtar"
        val model = "Pixel 8"
        val identifier = "12345"
        val version = "Android 14"

        composeRule.setContent {
            DetailScreen(
                identifier = identifier,
                model = model,
                version = version,
                name = name
            )
        }

        // Assert all labels and values are visible
        composeRule.onNodeWithText("Name").assertIsDisplayed()
        composeRule.onNodeWithText(name).assertIsDisplayed()

        composeRule.onNodeWithText("Model").assertIsDisplayed()
        composeRule.onNodeWithText(model).assertIsDisplayed()

        composeRule.onNodeWithText("Identifier").assertIsDisplayed()
        composeRule.onNodeWithText(identifier).assertIsDisplayed()

        composeRule.onNodeWithText("Android Version").assertIsDisplayed()
        composeRule.onNodeWithText(version).assertIsDisplayed()
    }
}