package com.dscoding.takenoteapp.presentation.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dscoding.takenoteapp.domain.model.UserPreferences
import com.dscoding.takenoteapp.domain.use_case.PreferencesUseCases
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
                    preferencesUseCases.updateUserPreference(
                        UserPreferences(
                            show_greeting = !showGreetingFieldState.value.isActive,
                            user_name = "",
                            theme = 0,
                            grid_layout_rows = 2
                        )
                    )
                }
            }
        }
    }

    private fun getPreferences() {
        getPreferencesJob?.cancel()
        getPreferencesJob = preferencesUseCases.getUserPreference()
            .onEach { preferences ->
                _showGreetingFieldState.value = showGreetingFieldState.value.copy(
                    value = if (preferences.show_greeting) "Enabled" else "Disabled",
                    isActive = preferences.show_greeting
                )
            }
            .launchIn(viewModelScope)
    }
}