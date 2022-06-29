package com.dscoding.takenoteapp.presentation.settings

import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.utils.UiText

data class SettingsState(
    val showThemeOptionsDialog: Boolean = false,
    val isUserNameFieldVisible: Boolean = false,
    val selectedTheme: UiText = UiText.StringResource(resId = R.string.empty_string)
)