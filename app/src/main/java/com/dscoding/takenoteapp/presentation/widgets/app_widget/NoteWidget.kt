package com.dscoding.takenoteapp.presentation.widgets.app_widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.currentState
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.dscoding.takenoteapp.common.Constants.NOTE_INVALID_COLOR
import com.dscoding.takenoteapp.common.Constants.NOTE_INVALID_ID

@ExperimentalFoundationApi
class NoteWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    private val _state = mutableStateOf(NoteWidgetState())
    val state: State<NoteWidgetState> = _state

    override val sizeMode: SizeMode = SizeMode.Exact

    @Composable
    override fun Content() {
        val prefs = currentState<Preferences>()
        if (state.value.noteId != prefs[NOTE_ID_KEY])
            _state.value = state.value.copy(
                noteId = prefs[NOTE_ID_KEY] ?: NOTE_INVALID_ID,
                noteTitle = prefs[NOTE_TITLE_KEY] ?: "",
                noteContent = prefs[NOTE_CONTENT_KEY] ?: "",
                noteColor = prefs[NOTE_COLOR_KEY] ?: NOTE_INVALID_COLOR
            )
        NoteWidgetScreen(noteWidgetState = state)
    }

    companion object {
        val NOTE_ID_KEY = intPreferencesKey("note_id_key")
        val NOTE_TITLE_KEY = stringPreferencesKey("note_title_key")
        val NOTE_CONTENT_KEY = stringPreferencesKey("note_content_key")
        val NOTE_COLOR_KEY = intPreferencesKey("note_color_key")
    }
}

@ExperimentalFoundationApi
class NoteWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = NoteWidget()
}