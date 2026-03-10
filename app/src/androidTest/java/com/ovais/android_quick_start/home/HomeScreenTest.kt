package com.ovais.android_quick_start.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ovais.android_quick_start.HiltTestActivity
import com.ovais.android_quick_start.features.home.presentation.HomeEffect
import com.ovais.android_quick_start.features.home.presentation.HomeIntent
import com.ovais.android_quick_start.features.home.presentation.HomeScreen
import com.ovais.android_quick_start.features.home.presentation.HomeUiState
import com.ovais.android_quick_start.features.home.presentation.HomeViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<HiltTestActivity>()

    // Simplified Fake ViewModel for tests
    class FakeHomeViewModel : HomeViewModel(GetDeviceInformationUseCaseFake()) {
        private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
        override val uiState: StateFlow<HomeUiState> = _uiState

        private val _effect = Channel<HomeEffect>()
        override val effect: Flow<HomeEffect> = _effect.receiveAsFlow()

        var lastIntent: HomeIntent? = null

        override fun handleIntent(intent: HomeIntent) {
            lastIntent = intent
        }

        fun setState(state: HomeUiState) {
            _uiState.value = state
        }
        
        suspend fun sendEffect(effect: HomeEffect) {
            _effect.send(effect)
        }
    }

    private lateinit var fakeViewModel: FakeHomeViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        fakeViewModel = FakeHomeViewModel()
    }

    @Test
    fun homeScreen_loadingState_displaysLoadingView() {
        fakeViewModel.setState(HomeUiState.Loading)

        composeRule.setContent {
            HomeScreen(
                viewModel = fakeViewModel,
                onNextClick = { _, _, _ -> }
            )
        }

        composeRule.onNodeWithTag("HomeLoadingView").assertIsDisplayed()
    }

    @Test
    fun homeScreen_successState_displaysSuccessView() {
        val model = "Pixel 8"
        val identifier = "12345"
        val version = "Android 14"

        fakeViewModel.setState(
            HomeUiState.Success(
                model = model,
                identifier = identifier,
                androidVersion = version
            )
        )

        composeRule.setContent {
            HomeScreen(
                viewModel = fakeViewModel,
                onNextClick = { _, _, _ -> }
            )
        }

        composeRule.onNodeWithTag("HomeSuccessView").assertIsDisplayed()
        composeRule.onNodeWithTag("ModelText").assertTextEquals(model)
        composeRule.onNodeWithTag("IdentifierText").assertTextEquals(identifier)
        composeRule.onNodeWithTag("VersionText").assertTextEquals(version)
    }

    @Test
    fun homeScreen_errorState_displaysErrorViewAndRetry() {
        val errorMessage = "Something went wrong"

        fakeViewModel.setState(HomeUiState.Error(errorMessage))

        composeRule.setContent {
            HomeScreen(
                viewModel = fakeViewModel,
                onNextClick = { _, _, _ -> }
            )
        }

        composeRule.onNodeWithTag("HomeErrorView").assertIsDisplayed()
        composeRule.onNodeWithTag("ErrorMessage").assertTextEquals(errorMessage)

        composeRule.onNodeWithTag("RetryButton").performClick()
        
        assert(fakeViewModel.lastIntent is HomeIntent.Refresh)
    }
}
