package com.dscoding.takenoteapp.presentation.settings

sealed class SettingsEvent {
    object ChangeShowGreetingState : SettingsEvent()
    object SelectRateTheApp : SettingsEvent()
    object SelectShareTheApp : SettingsEvent()
    data class ShowThemeOptionsDialog(val toShowDialog: Boolean) : SettingsEvent()
    data class SelectThemeOption(val option: Int) : SettingsEvent()

}