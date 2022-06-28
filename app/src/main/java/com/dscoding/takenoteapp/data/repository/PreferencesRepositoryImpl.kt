package com.dscoding.takenoteapp.data.repository

import androidx.datastore.preferences.core.edit
import com.dscoding.takenoteapp.data.data_source.PreferencesDataStore
import com.dscoding.takenoteapp.domain.model.UserPreferences
import com.dscoding.takenoteapp.domain.model.UserPreferences.Companion.GRID_LAYOUT_ROWS
import com.dscoding.takenoteapp.domain.model.UserPreferences.Companion.SHOW_GREETING
import com.dscoding.takenoteapp.domain.model.UserPreferences.Companion.THEME
import com.dscoding.takenoteapp.domain.model.UserPreferences.Companion.USER_NAME
import com.dscoding.takenoteapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.map

class PreferencesRepositoryImpl(
    private val dataStore: PreferencesDataStore
) : PreferencesRepository {

    override suspend fun savePreferences(userPreferences: UserPreferences) {
        dataStore.edit { preferences ->
            preferences[SHOW_GREETING] = userPreferences.show_greeting
            preferences[USER_NAME] = userPreferences.user_name
            preferences[THEME] = userPreferences.theme
            preferences[GRID_LAYOUT_ROWS] = userPreferences.grid_layout_rows
        }
    }

    override suspend fun readPreferences() = dataStore.data.map { userPreferences ->
        UserPreferences(
            show_greeting = userPreferences[SHOW_GREETING]!!,
            user_name = userPreferences[USER_NAME]!!,
            theme = userPreferences[THEME]!!,
            grid_layout_rows = userPreferences[GRID_LAYOUT_ROWS]!!
        )
    }


}