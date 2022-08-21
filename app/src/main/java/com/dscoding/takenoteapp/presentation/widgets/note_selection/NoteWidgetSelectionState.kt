package com.dscoding.takenoteapp.presentation.widgets.note_selection

import com.dscoding.takenoteapp.domain.model.Note

data class NoteWidgetSelectionState(
    val notes: List<Note> = emptyList(),
    val searchText: String = ""
)