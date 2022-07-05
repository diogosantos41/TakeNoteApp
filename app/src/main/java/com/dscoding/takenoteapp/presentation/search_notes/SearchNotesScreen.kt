package com.dscoding.takenoteapp.presentation.search_notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.presentation.list_notes.components.EmptyListAlert
import com.dscoding.takenoteapp.presentation.list_notes.components.NoteItem
import com.dscoding.takenoteapp.presentation.search_notes.components.SearchAppBar
import com.dscoding.takenoteapp.presentation.util.Screen
import com.dscoding.takenoteapp.utils.Constants
import com.dscoding.takenoteapp.utils.popBackToDashboard
import com.dscoding.takenoteapp.utils.safeNavigate

@Composable
fun SearchNotesScreen(
    navController: NavController,
    viewModel: SearchNotesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val focusRequester = remember { FocusRequester() }
    val generalMargin = dimensionResource(R.dimen.general_margin)

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            SearchAppBar(
                text = state.searchText,
                onTextChange = {
                    viewModel.onEvent(SearchNotesEvent.EnteredSearchText(it))
                },
                onCloseClicked = {
                    viewModel.onEvent(SearchNotesEvent.CleanSearchText)
                },
                onBackPressed = {
                    navController.popBackToDashboard()
                },
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
                if (state.notes.isEmpty()) {
                    EmptyListAlert(
                        stringResource(id = R.string.notes_search_empty_message)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(state.notes) { note ->
                            NoteItem(
                                note = note,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.safeNavigate(
                                            Screen.AddEditNoteScreen.route +
                                                    "?${Constants.NOTE_ID_ARG}=${note.id}&${Constants.NOTE_COLOR_ARG}=${note.color}"
                                        )
                                    },
                                showDeleteButton = false
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        })
}