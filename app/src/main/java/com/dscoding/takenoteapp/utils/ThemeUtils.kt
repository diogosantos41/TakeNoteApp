package com.dscoding.takenoteapp.utils

import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.domain.util.ThemeEnum
import com.dscoding.takenoteapp.ui.theme.TakeNoteTheme

object ThemeUtils {

    fun getThemeTextFromId(id: Int): UiText {
        return when (id) {
            ThemeEnum.SYSTEM_DEFAULT.id -> {
                UiText.StringResource(resId = R.string.settings_theme_option_system_default)
            }
            ThemeEnum.LIGHT.id -> {
                UiText.StringResource(resId = R.string.settings_theme_option_light)
            }
            ThemeEnum.DARK.id -> {
                UiText.StringResource(resId = R.string.settings_theme_option_dark)
            }
            ThemeEnum.DARK_YELLOW.id -> {
                UiText.StringResource(resId = R.string.settings_theme_option_yellow)
            }
            else -> {
                UiText.StringResource(resId = R.string.settings_theme_option_system_default)
            }
        }
    }

    fun getThemeFromId(id: Int): TakeNoteTheme {
        return when (id) {
            ThemeEnum.SYSTEM_DEFAULT.id -> {
                TakeNoteTheme.SYSTEM_DEFAULT
            }
            ThemeEnum.LIGHT.id -> {
                TakeNoteTheme.LIGHT
            }
            ThemeEnum.DARK.id -> {
                TakeNoteTheme.DARK
            }
            ThemeEnum.DARK_YELLOW.id -> {
                TakeNoteTheme.YELLOW
            }
            else -> {
                TakeNoteTheme.DARK
            }
        }
    }
}