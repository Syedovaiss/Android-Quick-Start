package com.ovais.android_quick_start.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovais.android_quick_start.features.home.domain.GetDeviceInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDeviceInformationUseCase: GetDeviceInformationUseCase
) : ViewModel() {

    private val _uiState by lazy { MutableStateFlow<HomeUiState>(HomeUiState.Loading) }
    val uiState: StateFlow<HomeUiState>
        get() = _uiState.asStateFlow()

    init {
        fetchDeviceInformation()
    }

    private fun fetchDeviceInformation() {
        viewModelScope.launch {
            try {
                delay(1500)
                val config = getDeviceInformationUseCase()
                _uiState.update {
                    HomeUiState.Success(
                        identifier = config.first,
                        model = config.second,
                        androidVersion = config.third
                    )
                }
            } catch (e: Exception) {
                Timber.e(e)
                _uiState.update {
                    HomeUiState.Error(e.message ?: "Something went wrong")
                }
            }
        }
    }
}