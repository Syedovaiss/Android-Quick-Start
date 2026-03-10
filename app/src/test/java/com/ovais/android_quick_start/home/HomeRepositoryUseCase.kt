package com.ovais.android_quick_start.home

import com.ovais.android_quick_start.base.BaseTest
import com.ovais.android_quick_start.core.config.DeviceConfigurationManager
import com.ovais.android_quick_start.features.home.data.HomeRepository
import com.ovais.android_quick_start.features.home.domain.DefaultHomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.util.UUID

class HomeRepositoryUseCase : BaseTest() {

    private lateinit var repository: HomeRepository

    @Mock
    private lateinit var deviceConfigurationManager: DeviceConfigurationManager

    override fun setup() {
        super.setup()
        repository = DefaultHomeRepository(deviceConfigurationManager, Dispatchers.IO)
    }

    @Test
    fun `get device information`() = runTest {
        val identifier = UUID.randomUUID().toString()
        val model = "Google Pixel"
        val androidVersion = "17-beta"
        `when`(deviceConfigurationManager.deviceIdentifier).thenReturn(identifier)
        `when`(deviceConfigurationManager.model).thenReturn(model)
        `when`(deviceConfigurationManager.androidVersion).thenReturn(androidVersion)
        val result = repository.getDeviceInformation()
        assert(result.first == identifier)
        assert(result.second == model)
        assert(result.third == androidVersion)
    }
}