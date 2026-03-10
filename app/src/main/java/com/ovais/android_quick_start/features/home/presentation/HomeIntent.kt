package com.ovais.android_quick_start.features.home.presentation
sealed interface HomeIntent {
    data object LoadDeviceInformation : HomeIntent
    data object Refresh : HomeIntent
}
