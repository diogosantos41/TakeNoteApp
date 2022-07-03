package com.dscoding.takenoteapp.presentation.add_edit_note

import com.dscoding.takenoteapp.utils.UiText

data class AddEditNoteState(
    val pageTitle: UiText.StringResource,
    val noteColor: Int,
    val isEditingNote: Boolean,
    val lastTimeEdited: String
)