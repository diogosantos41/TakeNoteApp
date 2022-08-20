package com.dscoding.takenoteapp.presentation.widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.dscoding.takenoteapp.presentation.search_notes.SearchNotesScreen
import com.dscoding.takenoteapp.ui.theme.TakeNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteWidgetSelectionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeNoteAppTheme {
                SearchNotesScreen(navController = rememberNavController())
            }
        }
    }
}