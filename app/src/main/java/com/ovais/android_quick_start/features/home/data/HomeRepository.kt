package com.ovais.android_quick_start.features.home.data

import com.ovais.android_quick_start.utils.DeviceInfo

interface HomeRepository {
    suspend fun getDeviceInformation(): DeviceInfo
}