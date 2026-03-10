package com.ovais.android_quick_start.features.home.presentation

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(
        val identifier: String,
        val model: String,
        val androidVersion: String
    ) : HomeUiState

    data class Error(val message: String) : HomeUiState
}
