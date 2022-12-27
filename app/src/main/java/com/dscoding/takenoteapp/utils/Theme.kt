package com.dscoding.takenoteapp.utils

import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.StringResource

enum class Theme(val id: Int, val stringResource: StringResource) {
    SystemDefault(0, StringResource(resId = R.string.settings_theme_option_system_default)),
    Light(1, StringResource(resId = R.string.settings_theme_option_light)),
    Dark(2, StringResource(resId = R.string.settings_theme_option_dark)),
    DarkYellow(3, StringResource(resId = R.string.settings_theme_option_dark_yellow)),
    Dynamic(4, StringResource(resId = R.string.settings_theme_option_dynamic))
}