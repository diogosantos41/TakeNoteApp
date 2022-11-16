package com.dscoding.takenoteapp.domain.model

import com.dscoding.takenoteapp.utils.Theme

data class PreferencesDto(
    val showGreeting: Boolean = true,
    val theme: Int = Theme.SystemDefault.id,
    val twentyFourHourClock: Boolean = true
)
