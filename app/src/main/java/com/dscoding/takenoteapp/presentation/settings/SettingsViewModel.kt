package com.dscoding.takenoteapp.presentation.settings

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.domain.model.UserPreferences
import com.dscoding.takenoteapp.domain.use_case.PreferencesUseCases
import com.dscoding.takenoteapp.ui.theme.TakeNoteTheme
import com.dscoding.takenoteapp.utils.ThemeUtils.getThemeFromId
import com.dscoding.takenoteapp.utils.ThemeUtils.getThemeIdFromText
import com.dscoding.takenoteapp.utils.ThemeUtils.getThemeTextFromId
import com.dscoding.takenoteapp.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

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
                            theme = getThemeIdFromText(state.value.selectedTheme as UiText.StringResource),
                        )
                    )
                }
            }
            is SettingsEvent.ShowThemeOptionsDialog -> {
                _state.value = state.value.copy(
                    showThemeOptionsDialog = event.toShowDialog
                )
            }
            is SettingsEvent.SelectThemeOption -> {
                _state.value = state.value.copy(
                    showThemeOptionsDialog = false,
                    selectedTheme = getThemeTextFromId(event.option)
                )
                viewModelScope.launch {
                    preferencesUseCases.updateUserPreference(
                        UserPreferences(
                            show_greeting = showGreetingFieldState.value.isActive,
                            theme = event.option
                        )
                    )
                    _eventFlow.emit(UiEvent.UpdateTheme(getThemeFromId(event.option)))
                }
            }
            is SettingsEvent.SelectRateTheApp -> {

            }
            is SettingsEvent.SelectShareTheApp -> {

            }
        }
    }

    private fun getPreferences() {
        getPreferencesJob?.cancel()
        getPreferencesJob = preferencesUseCases.getUserPreference()
            .onEach { preferences ->
                _showGreetingFieldState.value = showGreetingFieldState.value.copy(
                    value = if (preferences.show_greeting)
                        UiText.StringResource(
                            R.string.settings_show_greeting_enabled
                        )
                    else UiText.StringResource(
                        R.string.settings_show_greeting_disabled
                    ),
                    isActive = preferences.show_greeting
                )
                _state.value = state.value.copy(
                    selectedTheme = getThemeTextFromId(preferences.theme)
                )
            }
            .launchIn(viewModelScope)
    }

    sealed class UiEvent {
        data class UpdateTheme(val theme: TakeNoteTheme) : UiEvent()
    }
}

