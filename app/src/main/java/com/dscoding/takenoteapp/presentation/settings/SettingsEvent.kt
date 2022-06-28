package com.dscoding.takenoteapp.presentation.settings

sealed class SettingsEvent {
    object ChangeShowGreetingState : SettingsEvent()
    object OpenDialog : SettingsEvent()
    data class ShowDialog(val toShowDialog: Boolean) : SettingsEvent()
    data class SelectDialogOption(val option: Int) : SettingsEvent()
}