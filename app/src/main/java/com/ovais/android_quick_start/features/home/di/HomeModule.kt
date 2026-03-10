package com.ovais.android_quick_start.features.home.di

import com.ovais.android_quick_start.features.home.data.HomeRepository
import com.ovais.android_quick_start.features.home.domain.DefaultGetDeviceInformationUseCase
import com.ovais.android_quick_start.features.home.domain.DefaultHomeRepository
import com.ovais.android_quick_start.features.home.domain.GetDeviceInformationUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
interface HomeModule {
    @Binds
    fun bindHomeRepository(
        default: DefaultHomeRepository
    ): HomeRepository

    @Binds
    fun bindGetDeviceInfoUseCase(
        default: DefaultGetDeviceInformationUseCase
    ): GetDeviceInformationUseCase
}