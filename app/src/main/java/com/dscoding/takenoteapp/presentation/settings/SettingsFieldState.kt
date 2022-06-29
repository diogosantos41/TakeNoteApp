package com.dscoding.takenoteapp.presentation.settings

import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.utils.UiText

data class SettingsFieldState(
    val value: UiText = UiText.StringResource(R.string.empty_string),
    val isActive: Boolean = false
)