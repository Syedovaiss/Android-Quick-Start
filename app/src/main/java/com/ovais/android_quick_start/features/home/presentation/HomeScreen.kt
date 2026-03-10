package com.ovais.android_quick_start.features.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNextClick: (String, String, String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
                onRetry = {}
            )
        }
    }
}