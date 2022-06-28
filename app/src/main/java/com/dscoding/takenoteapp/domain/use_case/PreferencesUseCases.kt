package com.dscoding.takenoteapp.domain.use_case

data class PreferencesUseCases(
    val getUserPreference: GetPreferenceUseCase,
    val updateUserPreference: UpdatePreferenceUseCase
)

