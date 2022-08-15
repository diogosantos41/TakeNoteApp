package com.dscoding.takenoteapp.utils

import com.dscoding.takenoteapp.R

enum class TakeNoteTheme(val id: Int, val uiText: UiText) {
    SYSTEM_DEFAULT(0, UiText.StringResource(resId = R.string.settings_theme_option_system_default)),
    LIGHT(1, UiText.StringResource(resId = R.string.settings_theme_option_light)),
    DARK(2, UiText.StringResource(resId = R.string.settings_theme_option_dark)),
    DARK_YELLOW(3, UiText.StringResource(resId = R.string.settings_theme_option_dark_yellow))
}