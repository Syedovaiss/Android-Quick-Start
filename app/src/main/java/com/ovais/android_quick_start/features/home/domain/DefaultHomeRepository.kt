package com.ovais.android_quick_start.features.home.domain

import com.ovais.android_quick_start.core.config.DeviceConfigurationManager
import com.ovais.android_quick_start.features.home.data.HomeRepository
import com.ovais.android_quick_start.utils.DeviceInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultHomeRepository(
    private val deviceConfigurationManager: DeviceConfigurationManager,
    private val dispatcherIO: CoroutineDispatcher
) : HomeRepository {
    override suspend fun getDeviceInformation(): DeviceInfo {
        return withContext(dispatcherIO) {
            DeviceInfo(
                deviceConfigurationManager.deviceIdentifier,
                deviceConfigurationManager.model,
                deviceConfigurationManager.androidVersion
            )
        }
    }
}