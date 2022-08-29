package com.dscoding.takenoteapp.presentation.widgets.note_selection

import android.app.Activity.RESULT_OK
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.hilt.navigation.compose.hiltViewModel
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.presentation.common.NoteList
import com.dscoding.takenoteapp.presentation.search_notes.components.SearchAppBar
import com.dscoding.takenoteapp.presentation.widgets.app_widget.NoteWidget
import com.dscoding.takenoteapp.presentation.widgets.app_widget.NoteWidget.Companion.NOTE_COLOR_KEY
import com.dscoding.takenoteapp.presentation.widgets.app_widget.NoteWidget.Companion.NOTE_CONTENT_KEY
import com.dscoding.takenoteapp.presentation.widgets.app_widget.NoteWidget.Companion.NOTE_ID_KEY
import com.dscoding.takenoteapp.presentation.widgets.app_widget.NoteWidget.Companion.NOTE_TITLE_KEY
import com.dscoding.takenoteapp.utils.Constants.NOTE_INVALID_ID
import com.dscoding.takenoteapp.utils.extensions.findActivity
import com.dscoding.takenoteapp.utils.extensions.getGlanceId
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun NoteWidgetSelectionScreen(
    viewModel: NoteWidgetSelectionViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val coroutineScope = MainScope()
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val focusRequester = remember { FocusRequester() }
    val generalMargin = dimensionResource(R.dimen.margin)

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            SearchAppBar(
                text = state.searchText,
                onTextChange = {
                    viewModel.onEvent(NoteWidgetSelectionEvent.EnteredSearchText(it))
                },
                onCloseClicked = {
                    viewModel.onEvent(NoteWidgetSelectionEvent.CleanSearchText)
                },
                showBackButton = false,
                focusRequester = focusRequester
            )
        },
        scaffoldState = scaffoldState,
        content = { padding ->
            Column(
                modifier = Modifier.padding(
                    generalMargin,
                    generalMargin,
                    generalMargin,
                    0.dp
                )
            ) {
                NoteList(
                    notes = state.notes,
                    emptyMessage = stringResource(id = R.string.notes_search_empty_message),
                    showGridView = false,
                    showDeleteButton = false,
                    onNoteClicked = { note ->
                        coroutineScope.launch {
                            context.getGlanceId()?.let { glanceId ->
                                updateAppWidgetState(
                                    context = context.applicationContext,
                                    definition = PreferencesGlanceStateDefinition,
                                    glanceId = glanceId
                                ) { preferences ->
                                    preferences.toMutablePreferences().apply {
                                        set(NOTE_ID_KEY, note.id ?: NOTE_INVALID_ID)
                                        set(NOTE_TITLE_KEY, note.title)
                                        set(NOTE_CONTENT_KEY, note.content)
                                        set(NOTE_COLOR_KEY, note.color)
                                    }
                                }
                                NoteWidget().update(context.applicationContext, glanceId)
                            }
                        }
                        context.findActivity().setResult(RESULT_OK)
                        context.findActivity().finish()
                    }
                )
            }
        })
}