package com.dscoding.takenoteapp.presentation.util

import com.dscoding.takenoteapp.common.Constants.NOTE_COLOR_ARG
import com.dscoding.takenoteapp.common.Constants.NOTE_ID_ARG

sealed class Screen(val route: String) {
    object NotesScreen : Screen("notes_screen")
    object AddEditNoteScreen : Screen("add_edit_note_screen") {
        fun withArgs(noteId: String, noteColor: String) =
            route + "?$NOTE_ID_ARG=$noteId&$NOTE_COLOR_ARG=$noteColor"
    }
    object SearchNotesScreen : Screen("search_notes_screen")
    object SettingsScreen : Screen("settings_screen")
}