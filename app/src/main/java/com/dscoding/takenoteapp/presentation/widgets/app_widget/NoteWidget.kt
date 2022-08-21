package com.dscoding.takenoteapp.presentation.widgets.app_widget

import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.currentState
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.dscoding.takenoteapp.utils.Constants.NOTE_INVALID_ID

class NoteWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    @Composable
    override fun Content() {
        val prefs = currentState<Preferences>()
        val noteWidgetState =
            NoteWidgetState(
                noteId = prefs[NOTE_ID_KEY] ?: NOTE_INVALID_ID,
                noteTitle = prefs[NOTE_TITLE_KEY] ?: "",
                noteContent = prefs[NOTE_CONTENT_KEY] ?: ""
            )
        NoteWidgetScreen(noteWidgetState = noteWidgetState)
    }

    companion object {
        val NOTE_ID_KEY = intPreferencesKey("note_id_key")
        val NOTE_TITLE_KEY = stringPreferencesKey("note_title_key")
        val NOTE_CONTENT_KEY = stringPreferencesKey("note_content_key")
    }
}

class NoteWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = NoteWidget()
}