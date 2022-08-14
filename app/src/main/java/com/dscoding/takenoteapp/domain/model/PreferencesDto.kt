package com.dscoding.takenoteapp.domain.model

data class PreferencesDto(
    val showGreeting: Boolean,
    val theme: Int,
    val twentyFourHourClock: Boolean
)
