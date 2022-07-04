package com.dscoding.takenoteapp.presentation.search_notes

import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.domain.util.NoteOrder
import com.dscoding.takenoteapp.domain.util.OrderType

data class SearchNotesState(
    val notes: List<Note> = emptyList(),
    val searchText: String = ""
)