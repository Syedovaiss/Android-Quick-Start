package com.ovais.android_quick_start.features.home.presentation

sealed class HomeUiState {
    data class Success(
        val identifier: String,
        val model: String,
        val androidVersion: String
    ) : HomeUiState()

    object Loading : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}