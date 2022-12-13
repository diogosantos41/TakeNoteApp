package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.domain.data_store.SettingsDataStore
import com.dscoding.takenoteapp.domain.model.PreferencesDto
import kotlinx.coroutines.flow.Flow

class GetPreferencesUseCase(private val dataStore: SettingsDataStore) {

    operator fun invoke(): Flow<PreferencesDto> {
        return dataStore.getPreferences()
    }
}