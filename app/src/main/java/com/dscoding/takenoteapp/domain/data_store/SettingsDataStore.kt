package com.dscoding.takenoteapp.domain.data_store

import com.dscoding.takenoteapp.domain.model.PreferencesDto
import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {

    fun getPreferences(): Flow<PreferencesDto>

    suspend fun setShowGreeting(showGreeting: Boolean)

    suspend fun setTheme(theme: Int)

    suspend fun setFont(font: Int)

    suspend fun setTwentyFourHourClock(twentyFourHourClock: Boolean)
}