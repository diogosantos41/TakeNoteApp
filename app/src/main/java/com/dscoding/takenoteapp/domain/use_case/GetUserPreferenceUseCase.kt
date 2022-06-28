package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.domain.model.UserPreferences
import com.dscoding.takenoteapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class GetUserPreferenceUseCase(private val repository: PreferencesRepository) {

    suspend operator fun invoke(): Flow<UserPreferences> {
        return repository.readPreferences()
    }
}