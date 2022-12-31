package com.dscoding.takenoteapp.presentation.add_edit_note

import com.dscoding.takenoteapp.common.StringResource

data class NoteTextFieldState(
    val text: String = "",
    val hint: StringResource,
    val isHintVisible: Boolean = true
)