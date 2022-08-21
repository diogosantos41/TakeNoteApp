package com.dscoding.takenoteapp.presentation.widgets.app_widget

import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.currentState
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import dagger.hilt.android.AndroidEntryPoint

class NoteWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    @Composable
    override fun Content() {
        val prefs = currentState<Preferences>()
        val noteWidgetState = NoteWidgetState(
            noteTitle = "Title",
            noteContent = "Content"
           // noteTitle = prefs[NoteWidgetReceiver.noteTitle] ?: "",
           // noteContent = prefs[NoteWidgetReceiver.noteContent] ?: ""
        )
        NoteWidgetScreen(noteWidgetState)
    }

    companion object {
        val NOTE_ID_KEY = intPreferencesKey("note_id_key")
    }
}

@AndroidEntryPoint
class NoteWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = NoteWidget()
}