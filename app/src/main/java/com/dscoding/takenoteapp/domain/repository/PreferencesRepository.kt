package com.dscoding.takenoteapp.domain.repository

import com.dscoding.takenoteapp.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    suspend fun readPreferences(): Flow<UserPreferences>

    suspend fun savePreferences(userPreferences: UserPreferences)

}