package com.dscoding.takenoteapp.presentation.search_notes

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
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
import com.dscoding.takenoteapp.common.Constants
import com.dscoding.takenoteapp.presentation.common.NoteList
import com.dscoding.takenoteapp.presentation.search_notes.components.SearchAppBar
import com.dscoding.takenoteapp.presentation.util.Screen
import com.dscoding.takenoteapp.utils.extensions.popBackToDashboard
import com.dscoding.takenoteapp.utils.extensions.safeNavigate

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun SearchNotesScreen(
    navController: NavController,
    viewModel: SearchNotesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val focusRequester = remember { FocusRequester() }
    val generalMargin = dimensionResource(R.dimen.margin)

    LaunchedEffect(key1 = true) {
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
            ) {
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
                        onNoteClicked = {
                            navController.safeNavigate(
                                Screen.AddEditNoteScreen.route +
                                        "?${Constants.NOTE_ID_ARG}=${it.id}&${Constants.NOTE_COLOR_ARG}=${it.color}"
                            )
                        }
                    )
                }
            }
        })
}