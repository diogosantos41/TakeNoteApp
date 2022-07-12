package com.dscoding.takenoteapp.presentation.list_notes

import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class ClickDeleteNote(val note: Note) : NotesEvent()
    data class ShowConfirmDeleteNoteDialog(val toShowDialog: Boolean) : NotesEvent()
    object ConfirmDeleteNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
    object ToggleGridList : NotesEvent()
}