package com.ovais.android_quick_start.features.home.di

import com.ovais.android_quick_start.features.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val homeViewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }
}