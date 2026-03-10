package com.ovais.android_quick_start.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.retain.retain
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.ovais.android_quick_start.features.detail.presentation.DetailScreen
import com.ovais.android_quick_start.features.home.presentation.HomeScreen

@Composable
fun QuickStartNavigation() {
    val backStack = retain { mutableStateListOf<Routes>().apply { add(Routes.Home) } }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Routes.Home -> NavEntry(key) {
                    HomeScreen { model, identifier, version ->
                        backStack.add(Routes.Details(identifier, model, version))
                    }
                }

                is Routes.Details -> NavEntry(key) {
                    DetailScreen(key.deviceIdentifier, key.model, key.androidVersion)
                }
            }
        }
    )
}