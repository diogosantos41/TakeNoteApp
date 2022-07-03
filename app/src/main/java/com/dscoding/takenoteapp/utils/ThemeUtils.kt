package com.dscoding.takenoteapp.utils

import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.domain.model.UserPreferences
import com.dscoding.takenoteapp.ui.theme.TakeNoteTheme

object ThemeUtils {

    fun getThemeIdFromText(text: UiText.StringResource): Int {
        return if (text.equals(R.string.settings_theme_option_system_default)) {
            UserPreferences.Theme.SYSTEM_DEFAULT.id
        } else if (text.equals(R.string.settings_theme_option_light)) {
            UserPreferences.Theme.LIGHT.id
        } else if (text.equals(R.string.settings_theme_option_dark)) {
            UserPreferences.Theme.DARK.id
        } else if (text.equals(R.string.settings_theme_option_yellow)) {
            UserPreferences.Theme.YELLOW.id
        } else {
            UserPreferences.Theme.SYSTEM_DEFAULT.id
        }
    }

    fun getThemeTextFromId(id: Int): UiText {
        return when (id) {
            UserPreferences.Theme.SYSTEM_DEFAULT.id -> {
                UiText.StringResource(resId = R.string.settings_theme_option_system_default)
            }
            UserPreferences.Theme.LIGHT.id -> {
                UiText.StringResource(resId = R.string.settings_theme_option_light)
            }
            UserPreferences.Theme.DARK.id -> {
                UiText.StringResource(resId = R.string.settings_theme_option_dark)
            }
            UserPreferences.Theme.YELLOW.id -> {
                UiText.StringResource(resId = R.string.settings_theme_option_yellow)
            }
            else -> {
                UiText.StringResource(resId = R.string.settings_theme_option_system_default)
            }
        }
    }

    fun getThemeFromId(id: Int): TakeNoteTheme {
        return when (id) {
            UserPreferences.Theme.SYSTEM_DEFAULT.id -> {
                TakeNoteTheme.SYSTEM_DEFAULT
            }
            UserPreferences.Theme.LIGHT.id -> {
                TakeNoteTheme.LIGHT
            }
            UserPreferences.Theme.DARK.id -> {
                TakeNoteTheme.DARK
            }
            UserPreferences.Theme.YELLOW.id -> {
                TakeNoteTheme.YELLOW
            }
            else -> {
                TakeNoteTheme.DARK
            }
        }
    }
}