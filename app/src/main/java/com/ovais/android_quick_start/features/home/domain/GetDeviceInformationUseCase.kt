package com.ovais.android_quick_start.features.home.domain

import com.ovais.android_quick_start.features.home.data.HomeRepository
import com.ovais.android_quick_start.utils.DeviceInfo
import com.ovais.android_quick_start.utils.SuspendUseCase

interface GetDeviceInformationUseCase : SuspendUseCase<DeviceInfo>

class DefaultGetDeviceInformationUseCase(
    private val homeRepository: HomeRepository
) : GetDeviceInformationUseCase {
    override suspend fun invoke(): DeviceInfo {
        return homeRepository.getDeviceInformation()
    }
}