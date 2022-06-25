package com.dscoding.takenoteapp.feature_note.presentation.list_notes

import com.dscoding.takenoteapp.feature_note.domain.model.Note
import com.dscoding.takenoteapp.feature_note.domain.util.NoteOrder
import com.dscoding.takenoteapp.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
