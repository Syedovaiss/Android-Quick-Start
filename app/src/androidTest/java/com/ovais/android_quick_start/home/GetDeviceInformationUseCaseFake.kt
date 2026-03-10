package com.ovais.android_quick_start.home

import com.ovais.android_quick_start.features.home.domain.GetDeviceInformationUseCase
import com.ovais.android_quick_start.utils.DeviceInfo
import kotlinx.coroutines.delay

// Fake UseCase for testing
class GetDeviceInformationUseCaseFake(
    private val shouldFail: Boolean = false
) : GetDeviceInformationUseCase {

    override suspend fun invoke(): DeviceInfo {
        delay(100)
        return if (shouldFail) {
            throw Exception("Failed to fetch device info")
        } else {
            DeviceInfo(
                "FAKE123",
                "Pixel 8",
                "13"
            )
        }
    }
}