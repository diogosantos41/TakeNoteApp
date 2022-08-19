package com.dscoding.takenoteapp.presentation.widgets

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.dscoding.takenoteapp.domain.use_case.GetNotesUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class NoteWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = NoteWidget()

    private val coroutineScope = MainScope()

    @Inject
    lateinit var getNotesUseCase: GetNotesUseCase

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        observeData(context)
    }

    private fun observeData(context: Context) {
        coroutineScope.launch {

            val glanceId =
                GlanceAppWidgetManager(context).getGlanceIds(NoteWidget::class.java).firstOrNull()

            getNotesUseCase().collectLatest { notes ->
                glanceId?.let {
                    updateAppWidgetState(context, PreferencesGlanceStateDefinition, it) { pref ->
                        pref.toMutablePreferences().apply {
                            this[noteTitle] = notes[0].title
                            this[noteContent] = notes[0].content
                        }
                    }
                    glanceAppWidget.update(context, it)
                }
            }
        }
    }

    companion object {
        val noteTitle = stringPreferencesKey("noteTitle")
        val noteContent = stringPreferencesKey("noteContent")
    }
}