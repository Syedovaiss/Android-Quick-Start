package com.ovais.android_quick_start.features.home.domain

import com.ovais.android_quick_start.core.config.DeviceConfigurationManager
import com.ovais.android_quick_start.features.home.data.HomeRepository
import com.ovais.android_quick_start.utils.DeviceInfo
import com.ovais.android_quick_start.utils.dispatcher.Background
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultHomeRepository @Inject constructor(
    private val deviceConfigurationManager: DeviceConfigurationManager,
    @param:Background private val dispatcherIO: CoroutineDispatcher
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