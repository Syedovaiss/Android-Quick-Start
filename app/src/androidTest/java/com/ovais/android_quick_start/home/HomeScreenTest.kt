package com.ovais.android_quick_start.home

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ovais.android_quick_start.features.home.presentation.HomeEffect
import com.ovais.android_quick_start.features.home.presentation.HomeIntent
import com.ovais.android_quick_start.features.home.presentation.HomeScreen
import com.ovais.android_quick_start.features.home.presentation.HomeUiState
import com.ovais.android_quick_start.features.home.presentation.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_showsLoadingState() {
        val fakeViewModel = object : HomeViewModel(GetDeviceInformationUseCaseFake()) {
            override val uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
            override val effect = emptyFlow<HomeEffect>()
            override fun handleIntent(intent: HomeIntent) {}
        }

        composeTestRule.setContent {
            HomeScreen(
                viewModel = fakeViewModel,
                onNextClick = { _, _, _ -> }
            )
        }

        composeTestRule.onAllNodesWithTag("loading_item").assertCountEquals(4)
    }

    @Test
    fun homeScreen_showsSuccessState_andButtonWorks() {
        val fakeViewModel = object : HomeViewModel(GetDeviceInformationUseCaseFake()) {
            override val uiState = MutableStateFlow<HomeUiState>(
                HomeUiState.Success(
                    model = "Pixel 8",
                    identifier = "ABC123",
                    androidVersion = "13"
                )
            )
            override val effect = emptyFlow<HomeEffect>()
            override fun handleIntent(intent: HomeIntent) {}
        }

        var clicked = false

        composeTestRule.setContent {
            HomeScreen(
                viewModel = fakeViewModel,
                onNextClick = { model, id, version ->
                    clicked = true
                    assert(model == "Pixel 8")
                    assert(id == "ABC123")
                    assert(version == "13")
                }
            )
        }

        composeTestRule.onNodeWithText("📱 Model").assertIsDisplayed()
        composeTestRule.onNodeWithText("Pixel 8").assertIsDisplayed()
        composeTestRule.onNodeWithText("🆔 Identifier").assertIsDisplayed()
        composeTestRule.onNodeWithText("ABC123").assertIsDisplayed()

        composeTestRule.onNodeWithText("Continue").performClick()
        assert(clicked)
    }

    @Test
    fun homeScreen_showsErrorState_andRetryWorks() {
        var refreshCalled = false
        val fakeViewModel = object : HomeViewModel(GetDeviceInformationUseCaseFake()) {
            override val uiState = MutableStateFlow<HomeUiState>(
                HomeUiState.Error("Failed to load")
            )
            override val effect = emptyFlow<HomeEffect>()
            override fun handleIntent(intent: HomeIntent) {
                if (intent is HomeIntent.Refresh) {
                    refreshCalled = true
                }
            }
        }

        composeTestRule.setContent {
            HomeScreen(
                viewModel = fakeViewModel,
                onNextClick = { _, _, _ -> }
            )
        }

        composeTestRule.onNodeWithText("Failed to load").assertIsDisplayed()
        
        // Find retry button by text if it doesn't have a tag
        composeTestRule.onNodeWithText("Retry").performClick()
        assert(refreshCalled)
    }
}
