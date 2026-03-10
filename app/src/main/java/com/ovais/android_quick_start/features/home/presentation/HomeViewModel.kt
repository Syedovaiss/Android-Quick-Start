package com.ovais.android_quick_start.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovais.android_quick_start.features.home.domain.GetDeviceInformationUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

open class HomeViewModel(
    private val getDeviceInformationUseCase: GetDeviceInformationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    open val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _effect = Channel<HomeEffect>()
    open val effect: Flow<HomeEffect> = _effect.receiveAsFlow()

    init {
        handleIntent(HomeIntent.LoadDeviceInformation)
    }

    open fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadDeviceInformation -> fetchDeviceInformation()
            is HomeIntent.Refresh -> fetchDeviceInformation()
        }
    }

    private fun fetchDeviceInformation() {
        viewModelScope.launch {
            _uiState.update { HomeUiState.Loading }
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
                _effect.send(HomeEffect.ShowToast("Data Loaded Successfully"))
            } catch (e: Exception) {
                Timber.e(e)
                _uiState.update {
                    HomeUiState.Error(e.message ?: "Something went wrong")
                }
            }
        }
    }
}
