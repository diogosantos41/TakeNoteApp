package com.dscoding.takenoteapp.presentation.add_edit_note

import com.dscoding.takenoteapp.common.StringResource

data class AddEditNoteState(
    val pageTitle: StringResource,
    val noteColor: Int,
    val isEditingNote: Boolean,
    val lastTimeEdited: String,
    val showDeleteConfirmationDialog: Boolean = false
)