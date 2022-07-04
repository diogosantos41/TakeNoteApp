package com.dscoding.takenoteapp.presentation.util

sealed class Screen(val route: String) {
    object NotesScreen: Screen("notes_screen")
    object AddEditNoteScreen: Screen("add_edit_note_screen")
    object SearchNotesScreen: Screen("search_notes_screen")
    object SettingsScreen: Screen("settings_screen")
}