package com.ovais.android_quick_start.core.navigation

sealed interface Routes {
    object Home : Routes

    data class Details(
        val deviceIdentifier: String,
        val model: String,
        val androidVersion: String
    ) : Routes
}