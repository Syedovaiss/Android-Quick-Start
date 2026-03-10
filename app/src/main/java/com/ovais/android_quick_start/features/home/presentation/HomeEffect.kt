package com.ovais.android_quick_start.features.home.presentation
sealed interface HomeEffect {
    data class ShowToast(val message: String) : HomeEffect
}
