package com.ovais.android_quick_start.core.config

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface ConfigurationModule {
    @Binds
    @Singleton
    fun bindDeviceConfigurationManager(
        default: DefaultDeviceConfigurationManager
    ): DeviceConfigurationManager
}