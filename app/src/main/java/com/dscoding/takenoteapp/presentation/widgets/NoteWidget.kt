package com.dscoding.takenoteapp.presentation.widgets

import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.Preferences
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.currentState
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition

class NoteWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    @Composable
    override fun Content() {
        val prefs = currentState<Preferences>()
        val noteWidgetState = NoteWidgetState(
            noteTitle = prefs[NoteWidgetReceiver.noteTitle] ?: "",
            noteContent = prefs[NoteWidgetReceiver.noteContent] ?: ""
        )
        NoteWidgetScreen(noteWidgetState)

    }
}