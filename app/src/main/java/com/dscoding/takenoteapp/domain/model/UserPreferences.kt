package com.dscoding.takenoteapp.domain.model

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey

data class UserPreferences(
    val show_greeting: Boolean,
    val theme: Int,
    val twenty_four_hour_clock: Boolean
) {
    companion object {
        val SHOW_GREETING = booleanPreferencesKey("show_greeting")
        val THEME = intPreferencesKey("theme")
        val TWENTY_FOUR_HOUR_CLOCK = booleanPreferencesKey("twenty_four_hour_clock")
    }

    enum class Theme(val id: Int) {
        SYSTEM_DEFAULT(0),
        LIGHT(1),
        DARK(2),
        YELLOW(3)
    }
}