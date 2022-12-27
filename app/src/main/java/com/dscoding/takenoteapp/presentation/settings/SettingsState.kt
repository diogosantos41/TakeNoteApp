package com.dscoding.takenoteapp.presentation.settings

import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.StringResource

data class SettingsState(
    val showThemeOptionsDialog: Boolean = false,
    val isUserNameFieldVisible: Boolean = false,
    val selectedTheme: StringResource = StringResource(resId = R.string.empty_string)
)