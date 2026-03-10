package com.ovais.android_quick_start.home

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ovais.android_quick_start.features.home.presentation.HomeScreen
import com.ovais.android_quick_start.features.home.presentation.HomeUiState
import com.ovais.android_quick_start.features.home.presentation.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_showsLoadingState() {
        // Arrange: Fake ViewModel emitting Loading
        val fakeViewModel = object : HomeViewModel(GetDeviceInformationUseCaseFake()) {
            override val uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
        }

        // Act
        composeTestRule.setContent {
            HomeScreen(
                viewModel = fakeViewModel,
                onNextClick = { _, _, _ -> }
            )
        }

        // Assert: Check that shimmer/loading items exist
        composeTestRule.onAllNodesWithTag("loading_item").assertCountEquals(4)
    }

    @Test
    fun homeScreen_showsSuccessState_andButtonWorks() {
        // Arrange: Fake ViewModel emitting Success
        val fakeViewModel = object : HomeViewModel(GetDeviceInformationUseCaseFake()) {
            override val uiState = MutableStateFlow<HomeUiState>(
                HomeUiState.Success(
                    model = "Pixel 8",
                    identifier = "ABC123",
                    androidVersion = "13"
                )
            )
        }

        var clicked = false

        // Act
        composeTestRule.setContent {
            HomeScreen(
                viewModel = fakeViewModel,
                onNextClick = { model, id, version ->
                    clicked = true
                    // optionally assert values
                    assert(model == "Pixel 8")
                    assert(id == "ABC123")
                    assert(version == "13")
                }
            )
        }

        // Assert: Check text is displayed
        composeTestRule.onNodeWithText("📱 Model").assertIsDisplayed()
        composeTestRule.onNodeWithText("Pixel 8").assertIsDisplayed()
        composeTestRule.onNodeWithText("🆔 Identifier").assertIsDisplayed()
        composeTestRule.onNodeWithText("ABC123").assertIsDisplayed()

        // Click Continue button
        composeTestRule.onNodeWithText("Continue").performClick()
        assert(clicked)
    }

    @Test
    fun homeScreen_showsErrorState() {
        // Arrange: Fake ViewModel emitting Error
        val fakeViewModel = object : HomeViewModel(GetDeviceInformationUseCaseFake()) {
            override val uiState = MutableStateFlow<HomeUiState>(
                HomeUiState.Error("Failed to load")
            )
        }

        // Act
        composeTestRule.setContent {
            HomeScreen(
                viewModel = fakeViewModel,
                onNextClick = { _, _, _ -> }
            )
        }

        // Assert: Error message is shown
        composeTestRule.onNodeWithText("Failed to load").assertIsDisplayed()
    }
}