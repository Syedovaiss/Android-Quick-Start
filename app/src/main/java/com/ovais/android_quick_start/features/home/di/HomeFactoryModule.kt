package com.ovais.android_quick_start.features.home.di

import com.ovais.android_quick_start.core.di.BACKGROUND
import com.ovais.android_quick_start.features.home.data.HomeRepository
import com.ovais.android_quick_start.features.home.domain.DefaultGetDeviceInformationUseCase
import com.ovais.android_quick_start.features.home.domain.DefaultHomeRepository
import com.ovais.android_quick_start.features.home.domain.GetDeviceInformationUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module


val homeFactoryModule = module {
    factory<HomeRepository> {
        DefaultHomeRepository(
            get(),
            get(named(BACKGROUND))
        )
    }

    factory<GetDeviceInformationUseCase> {
        DefaultGetDeviceInformationUseCase(get())
    }
}