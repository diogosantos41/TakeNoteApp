package com.dscoding.takenoteapp.presentation.widgets.app_widget

data class NoteWidgetState(
    val noteId: Int,
    val noteTitle: String = "",
    val noteContent: String = "",
)