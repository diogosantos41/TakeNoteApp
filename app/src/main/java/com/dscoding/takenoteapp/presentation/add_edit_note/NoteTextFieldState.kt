package com.dscoding.takenoteapp.presentation.add_edit_note

import com.dscoding.takenoteapp.utils.UiText

data class NoteTextFieldState(
    val text: String = "",
    val hint: UiText,
    val isHintVisible: Boolean = true
)