package com.dscoding.takenoteapp.presentation.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.StringResource
import com.dscoding.takenoteapp.domain.use_case.PreferencesUseCases
import com.dscoding.takenoteapp.utils.extensions.logSwapTheme
import com.dscoding.takenoteapp.utils.getAppTheme
import com.dscoding.takenoteapp.utils.getThemeText
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesUseCases: PreferencesUseCases
) : ViewModel() {

    private val _showGreetingFieldState = mutableStateOf(SettingsFieldState())
    val showGreetingFieldState: State<SettingsFieldState> = _showGreetingFieldState

    private val _isTwentyFourHourClockFieldState = mutableStateOf(SettingsFieldState())
    val twentyFourHourClockFieldState: State<SettingsFieldState> =
        _isTwentyFourHourClockFieldState

    private val _state = mutableStateOf(SettingsState())
    val state: State<SettingsState> = _state

    private var getPreferencesJob: Job? = null

    init {
        getPreferences()
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ChangeShowGreetingState -> {
                viewModelScope.launch {
                    preferencesUseCases.updatePreferences
                        .setShowGreeting(
                            !showGreetingFieldState.value.isActive
                        )
                }
            }
            is SettingsEvent.ChangeTwentyFourHourClockState -> {
                viewModelScope.launch {
                    preferencesUseCases.updatePreferences
                        .setTwentyFourHourClock(
                            !twentyFourHourClockFieldState.value.isActive
                        )
                }
            }
            is SettingsEvent.ShowThemeOptionsDialog -> {
                _state.value = state.value.copy(
                    showThemeOptionsDialog = event.toShowDialog
                )
            }
            is SettingsEvent.SelectThemeOption -> {
                if (state.value.selectedTheme != getThemeText(event.option)) {
                    Firebase.analytics.logSwapTheme(getAppTheme(event.option).name)
                }
                _state.value = state.value.copy(
                    showThemeOptionsDialog = false,
                    selectedTheme = getThemeText(event.option)
                )
                viewModelScope.launch {
                    preferencesUseCases.updatePreferences
                        .setTheme(
                            event.option
                        )
                }
            }
        }
    }

    private fun getPreferences() {
        getPreferencesJob?.cancel()
        getPreferencesJob = preferencesUseCases.getPreferences()
            .onEach { preferences ->
                _showGreetingFieldState.value = showGreetingFieldState.value.copy(
                    value = if (preferences.showGreeting)
                        StringResource(
                            R.string.generic_enabled
                        )
                    else StringResource(
                        R.string.generic_disabled
                    ),
                    isActive = preferences.showGreeting
                )
                _isTwentyFourHourClockFieldState.value = twentyFourHourClockFieldState.value.copy(
                    value = if (preferences.twentyFourHourClock)
                        StringResource(
                            R.string.generic_enabled
                        )
                    else StringResource(
                        R.string.generic_disabled
                    ),
                    isActive = preferences.twentyFourHourClock
                )
                _state.value = state.value.copy(
                    selectedTheme = getThemeText(preferences.theme)
                )
            }
            .launchIn(viewModelScope)
    }
}
