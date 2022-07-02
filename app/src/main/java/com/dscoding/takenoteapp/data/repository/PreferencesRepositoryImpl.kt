package com.dscoding.takenoteapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.dscoding.takenoteapp.domain.model.UserPreferences
import com.dscoding.takenoteapp.domain.model.UserPreferences.Companion.SHOW_GREETING
import com.dscoding.takenoteapp.domain.model.UserPreferences.Companion.THEME
import com.dscoding.takenoteapp.domain.repository.PreferencesRepository
import com.dscoding.takenoteapp.utils.Constants.DATASTORE_NAME
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class PreferencesRepositoryImpl(
    private val context: Context
) : PreferencesRepository {

    override suspend fun updatePreferences(userPreferences: UserPreferences) {
        context.dataStore.edit { preferences ->
            preferences[SHOW_GREETING] = userPreferences.show_greeting
            preferences[THEME] = userPreferences.theme
        }
    }

    override fun getPreferences() = context.dataStore.data.map { userPreferences ->
        UserPreferences(
            show_greeting = userPreferences[SHOW_GREETING] ?: true,
            theme = userPreferences[THEME] ?: UserPreferences.Theme.SYSTEM_DEFAULT.id,
        )
    }
}