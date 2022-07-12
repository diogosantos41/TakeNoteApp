package com.dscoding.takenoteapp.presentation.search_notes

import com.dscoding.takenoteapp.domain.model.Note

data class SearchNotesState(
    val notes: List<Note> = emptyList(),
    val searchText: String = ""
)