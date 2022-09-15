package com.dscoding.takenoteapp.data.data_store

import com.dscoding.takenoteapp.domain.data_store.SettingsDataStore
import com.dscoding.takenoteapp.domain.model.PreferencesDto
import com.dscoding.takenoteapp.utils.TakeNoteTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSettingsDataStore : SettingsDataStore {

    private var preferences = PreferencesDto(
        showGreeting = true,
        theme = TakeNoteTheme.SYSTEM_DEFAULT.id,
        twentyFourHourClock = true
    )


    override fun getPreferences(): Flow<PreferencesDto> {
        return flow { emit(preferences) }
    }

    override suspend fun setShowGreeting(showGreeting: Boolean) {
        preferences.showGreeting = showGreeting
    }

    override suspend fun setTheme(theme: Int) {
        preferences.theme = theme
    }

    override suspend fun setTwentyFourHourClock(twentyFourHourClock: Boolean) {
        preferences.twentyFourHourClock = twentyFourHourClock
    }
}