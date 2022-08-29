package com.dscoding.takenoteapp.presentation.widgets.app_widget

import com.dscoding.takenoteapp.utils.Constants.NOTE_INVALID_COLOR
import com.dscoding.takenoteapp.utils.Constants.NOTE_INVALID_ID

data class NoteWidgetState(
    val noteId: Int = NOTE_INVALID_ID,
    val noteTitle: String = "",
    val noteContent: String = "",
    val noteColor: Int = NOTE_INVALID_COLOR
)