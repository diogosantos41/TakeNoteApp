package com.dscoding.takenoteapp.presentation.list_notes

import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}