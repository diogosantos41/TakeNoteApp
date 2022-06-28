package com.dscoding.takenoteapp.presentation.settings

sealed class SettingsEvent {
    object ChangeShowGreetingState : SettingsEvent()
}