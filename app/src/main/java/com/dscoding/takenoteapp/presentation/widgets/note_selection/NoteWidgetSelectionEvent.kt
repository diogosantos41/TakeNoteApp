package com.dscoding.takenoteapp.presentation.widgets.note_selection

sealed class NoteWidgetSelectionEvent {
    data class EnteredSearchText(val value: String) : NoteWidgetSelectionEvent()
    object CleanSearchText : NoteWidgetSelectionEvent()
}