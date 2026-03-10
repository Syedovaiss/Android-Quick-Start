package com.ovais.android_quick_start.core.di

import com.ovais.android_quick_start.core.config.DefaultDeviceConfigurationManager
import com.ovais.android_quick_start.core.config.DeviceConfigurationManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val BACKGROUND = "BACKGROUND"
const val DEFAULT = "DEFAULT"
const val MAIN = "MAIN"

val singletonModule = module {
    single<CoroutineDispatcher>(named(BACKGROUND)) {
        Dispatchers.IO
    }
    single<CoroutineDispatcher>(named(DEFAULT)) {
        Dispatchers.Default
    }
    single<CoroutineDispatcher>(named(MAIN)) {
        Dispatchers.Main
    }
    single<DeviceConfigurationManager> { DefaultDeviceConfigurationManager(get()) }
}