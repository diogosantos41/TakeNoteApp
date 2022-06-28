package com.dscoding.takenoteapp.presentation.settings

import androidx.lifecycle.ViewModel
import com.dscoding.takenoteapp.domain.use_case.PreferencesUseCases
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val preferencesUseCases: PreferencesUseCases
) : ViewModel() {

}