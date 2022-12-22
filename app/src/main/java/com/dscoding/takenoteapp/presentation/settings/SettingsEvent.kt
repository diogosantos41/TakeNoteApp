package com.dscoding.takenoteapp.presentation.settings

sealed interface SettingsEvent {
    object ChangeShowGreetingState : SettingsEvent
    object ChangeTwentyFourHourClockState : SettingsEvent
    data class ShowThemeOptionsDialog(val toShowDialog: Boolean) : SettingsEvent
    data class SelectThemeOption(val option: Int) : SettingsEvent
}