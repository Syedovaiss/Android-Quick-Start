package com.ovais.android_quick_start.home

import com.ovais.android_quick_start.base.BaseTest
import com.ovais.android_quick_start.features.home.domain.GetDeviceInformationUseCase
import com.ovais.android_quick_start.features.home.presentation.HomeUiState
import com.ovais.android_quick_start.features.home.presentation.HomeViewModel
import com.ovais.android_quick_start.utils.DeviceInfo
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : BaseTest() {
    private lateinit var viewModel: HomeViewModel

    @Mock
    private lateinit var getDeviceInformationUseCase: GetDeviceInformationUseCase

    override fun setup() {
        super.setup()
        viewModel = HomeViewModel(getDeviceInformationUseCase)
    }


    @Test
    fun `fetch device configuration`() = runTest {
        `when`(getDeviceInformationUseCase()).thenReturn(DeviceInfo("12345", "Samsung", "13"))

        viewModel.fetchDeviceInformation()

        advanceUntilIdle()

        // Assert
        val state = viewModel.uiState.value
        assertTrue(state is HomeUiState.Success)
        state as HomeUiState.Success
        assertEquals("12345", state.identifier)
        assertEquals("Samsung", state.model)
        assertEquals("13", state.androidVersion)
    }

    @Test
    fun `fetch device configuration when error`() = runTest {
        // Arrange: make the use case throw an exception
        `when`(getDeviceInformationUseCase()).thenThrow(RuntimeException("Failed to fetch"))

        // Act
        viewModel.fetchDeviceInformation()
        advanceUntilIdle()

        // Assert
        val state = viewModel.uiState.value
        assertTrue(state is HomeUiState.Error)
        state as HomeUiState.Error
        assertEquals("Failed to fetch", state.message)
    }

    @Test
    fun reinitialize() = runTest {
        `when`(getDeviceInformationUseCase()).thenReturn(DeviceInfo("12345", "Samsung", "13"))

        viewModel.reInitialize()

        advanceUntilIdle()

        // Assert
        val state = viewModel.uiState.value
        assertTrue(state is HomeUiState.Success)
        state as HomeUiState.Success
        assertEquals("12345", state.identifier)
        assertEquals("Samsung", state.model)
        assertEquals("13", state.androidVersion)
    }
}