package com.dscoding.takenoteapp.presentation.search_notes

sealed class SearchNotesEvent {
    data class EnteredSearchText(val value: String) : SearchNotesEvent()
    object CleanSearchText : SearchNotesEvent()
}