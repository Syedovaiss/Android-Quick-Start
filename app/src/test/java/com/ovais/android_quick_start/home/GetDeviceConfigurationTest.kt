package com.ovais.android_quick_start.home

import com.ovais.android_quick_start.base.BaseTest
import com.ovais.android_quick_start.features.home.data.HomeRepository
import com.ovais.android_quick_start.features.home.domain.DefaultGetDeviceInformationUseCase
import com.ovais.android_quick_start.features.home.domain.GetDeviceInformationUseCase
import com.ovais.android_quick_start.utils.DeviceInfo
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.util.UUID

class GetDeviceConfigurationTest : BaseTest() {

    private lateinit var useCase: GetDeviceInformationUseCase

    @Mock
    private lateinit var repository: HomeRepository
    override fun setup() {
        super.setup()
        useCase = DefaultGetDeviceInformationUseCase(repository)
    }

    @Test
    fun `invoke use case`() = runTest {
        val deviceInfo = DeviceInfo(UUID.randomUUID().toString(), "Pixel", "17")
        `when`(repository.getDeviceInformation()).thenReturn(deviceInfo)
        val result = useCase()

        assertEquals(result, deviceInfo)
    }
}