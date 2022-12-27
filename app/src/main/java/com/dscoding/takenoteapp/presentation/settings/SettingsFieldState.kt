package com.dscoding.takenoteapp.presentation.settings

import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.StringResource

data class SettingsFieldState(
    val value: StringResource = StringResource(R.string.empty_string),
    val isActive: Boolean = false
)