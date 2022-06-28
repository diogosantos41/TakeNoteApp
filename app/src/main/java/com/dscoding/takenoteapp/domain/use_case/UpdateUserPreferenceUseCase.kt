package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.domain.model.UserPreferences
import com.dscoding.takenoteapp.domain.repository.PreferencesRepository

class UpdateUserPreferenceUseCase(private val repository: PreferencesRepository) {

    suspend operator fun invoke(userPreferences: UserPreferences) {
        repository.savePreferences(userPreferences)
    }
}