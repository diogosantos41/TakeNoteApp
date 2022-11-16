package com.dscoding.takenoteapp.data.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dscoding.takenoteapp.domain.data_store.SettingsDataStore
import com.dscoding.takenoteapp.common.Constants.DATASTORE_NAME
import com.dscoding.takenoteapp.domain.model.PreferencesDto
import com.dscoding.takenoteapp.utils.Theme
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class SettingsDataStoreImpl(
    private val context: Context
) : SettingsDataStore {


    override fun getPreferences() = context.dataStore.data.map { preferences ->
        PreferencesDto(
            showGreeting = preferences[SHOW_GREETING] ?: true,
            theme = preferences[THEME] ?: Theme.SystemDefault.id,
            twentyFourHourClock = preferences[TWENTY_FOUR_HOUR_CLOCK] ?: true
        )
    }

    override suspend fun setShowGreeting(showGreeting: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SHOW_GREETING] = showGreeting
        }
    }

    override suspend fun setTheme(theme: Int) {
        context.dataStore.edit { preferences ->
            preferences[THEME] = theme
        }
    }

    override suspend fun setTwentyFourHourClock(twentyFourHourClock: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[TWENTY_FOUR_HOUR_CLOCK] = twentyFourHourClock
        }
    }

    companion object {
        val SHOW_GREETING = booleanPreferencesKey("show_greeting")
        val THEME = intPreferencesKey("theme")
        val TWENTY_FOUR_HOUR_CLOCK = booleanPreferencesKey("twenty_four_hour_clock")
    }
}