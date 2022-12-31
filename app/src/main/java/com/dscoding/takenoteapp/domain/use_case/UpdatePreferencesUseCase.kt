package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.domain.data_store.SettingsDataStore

class UpdatePreferencesUseCase(private val dataStore: SettingsDataStore) {

    suspend fun setShowGreeting(isShowGreeting: Boolean) {
        dataStore.setShowGreeting(isShowGreeting)
    }

    suspend fun setTheme(theme: Int) {
        dataStore.setTheme(theme)
    }

    suspend fun setFont(font: Int) {
        dataStore.setFont(font)
    }

    suspend fun setTwentyFourHourClock(isTwentyFourHourClock: Boolean) {
        dataStore.setTwentyFourHourClock(isTwentyFourHourClock)
    }
}