package com.dscoding.takenoteapp.domain.repository

import com.dscoding.takenoteapp.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    fun getPreferences(): Flow<UserPreferences>

    suspend fun updatePreferences(userPreferences: UserPreferences)

}