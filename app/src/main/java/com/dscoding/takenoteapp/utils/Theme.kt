package com.dscoding.takenoteapp.utils

import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.UiText

enum class Theme(val id: Int, val uiText: UiText) {
    SystemDefault(0, UiText.StringResource(resId = R.string.settings_theme_option_system_default)),
    Light(1, UiText.StringResource(resId = R.string.settings_theme_option_light)),
    Dark(2, UiText.StringResource(resId = R.string.settings_theme_option_dark)),
    DarkYellow(3, UiText.StringResource(resId = R.string.settings_theme_option_dark_yellow)),
    Dynamic(4, UiText.StringResource(resId = R.string.settings_theme_option_dynamic))
}