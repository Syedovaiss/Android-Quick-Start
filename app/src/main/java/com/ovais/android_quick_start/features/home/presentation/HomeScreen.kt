package com.ovais.android_quick_start.features.home.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNextClick: (String, String, String) -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is HomeEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    when (uiState) {
        is HomeUiState.Loading -> HomeLoadingView()

        is HomeUiState.Success -> {
            val state = (uiState as HomeUiState.Success)
            HomeSuccessView(
                state.model,
                state.identifier,
                state.androidVersion,
                onNextClick = onNextClick
            )
        }

        is HomeUiState.Error -> {
            val state = (uiState as HomeUiState.Error)
            HomeErrorView(
                message = state.message,
                onRetry = {
                    viewModel.handleIntent(HomeIntent.Refresh)
                }
            )
        }
    }
}
