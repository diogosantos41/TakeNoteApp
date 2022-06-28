package com.dscoding.takenoteapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.dscoding.takenoteapp.domain.model.UserPreferences
import com.dscoding.takenoteapp.domain.model.UserPreferences.Companion.GRID_LAYOUT_ROWS
import com.dscoding.takenoteapp.domain.model.UserPreferences.Companion.SHOW_GREETING
import com.dscoding.takenoteapp.domain.model.UserPreferences.Companion.THEME
import com.dscoding.takenoteapp.domain.model.UserPreferences.Companion.USER_NAME
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
            preferences[USER_NAME] = userPreferences.user_name
            preferences[THEME] = userPreferences.theme
            preferences[GRID_LAYOUT_ROWS] = userPreferences.grid_layout_rows
        }
    }

    override fun getPreferences() = context.dataStore.data.map { userPreferences ->
        UserPreferences(
            show_greeting = userPreferences[SHOW_GREETING] ?: true,
            user_name = userPreferences[USER_NAME] ?: "",
            theme = userPreferences[THEME] ?: UserPreferences.Theme.SYSTEM_DEFAULT.int,
            grid_layout_rows = userPreferences[GRID_LAYOUT_ROWS] ?: 2
        )
    }
}