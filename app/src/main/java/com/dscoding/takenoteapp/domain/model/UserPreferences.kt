package com.dscoding.takenoteapp.domain.model

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dscoding.takenoteapp.R

data class UserPreferences(
    val show_greeting: Boolean,
    val user_name: String,
    val theme: Int,
) {
    companion object {
        val SHOW_GREETING = booleanPreferencesKey("show_greeting")
        val USER_NAME = stringPreferencesKey("user_name")
        val THEME = intPreferencesKey("theme")
    }

    enum class Theme(val id: Int) {
        SYSTEM_DEFAULT(0),
        LIGHT(1),
        DARK(2)
    }
}