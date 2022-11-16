package com.dscoding.takenoteapp.domain.model

import com.dscoding.takenoteapp.utils.Theme

data class PreferencesDto(
    var showGreeting: Boolean = true,
    var theme: Int = Theme.SystemDefault.id,
    var twentyFourHourClock: Boolean = true
)
