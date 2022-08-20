package com.dscoding.takenoteapp.presentation.list_notes

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.presentation.common.ConfirmationDialog
import com.dscoding.takenoteapp.presentation.common.NoteList
import com.dscoding.takenoteapp.presentation.common.SnackbarHostController
import com.dscoding.takenoteapp.presentation.list_notes.components.GreetingSection
import com.dscoding.takenoteapp.presentation.list_notes.components.OrderSection
import com.dscoding.takenoteapp.presentation.util.Screen
import com.dscoding.takenoteapp.ui.theme.ThemeManager
import com.dscoding.takenoteapp.utils.Constants.NOTE_COLOR_ARG
import com.dscoding.takenoteapp.utils.Constants.NOTE_ID_ARG
import com.dscoding.takenoteapp.utils.extensions.safeNavigate
import kotlinx.coroutines.flow.collectLatest

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val generalMargin = dimensionResource(R.dimen.margin)

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is NotesViewModel.UiEvent.UpdateTheme -> {
                    ThemeManager.takeNoteTheme = event.theme
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = ThemeManager.colors.mainColor,
                onClick = {
                    navController.safeNavigate(Screen.AddEditNoteScreen.route)
                }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add note",
                    tint = ThemeManager.colors.iconColor
                )
            }
        },
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar(
                backgroundColor = ThemeManager.colors.mainColor,
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                )
            ) {
                IconButton(
                    onClick = { navController.safeNavigate(Screen.SearchNotesScreen.route) },
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search notes",
                        tint = ThemeManager.colors.iconColor
                    )
                }
                IconButton(
                    onClick = {
                        viewModel.onEvent(NotesEvent.ToggleGridList)
                    },
                ) {
                    Icon(
                        imageVector = if (state.isGridListSelected) Icons.Default.List else Icons.Default.GridView,
                        contentDescription = "Toggle Grid View/List View",
                        tint = ThemeManager.colors.iconColor
                    )
                }
                IconButton(
                    onClick = { navController.safeNavigate(Screen.SettingsScreen.route) },
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = ThemeManager.colors.iconColor
                    )
                }
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = { SnackbarHostController(it) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        generalMargin,
                        0.dp,
                        generalMargin,
                        0.dp
                    )
            ) {
                if (state.isGreetingSectionVisible) {
                    GreetingSection()
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.notes_list_title),
                        style = MaterialTheme.typography.h5,
                        color = ThemeManager.colors.textColor
                    )
                    IconButton(
                        onClick = {
                            viewModel.onEvent(NotesEvent.ToggleOrderSection)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Sort,
                            contentDescription = "Sort Notes",
                            tint = ThemeManager.colors.iconColor
                        )
                    }
                }
                AnimatedVisibility(
                    visible = state.isOrderSectionVisible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    OrderSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = generalMargin),
                        noteOrder = state.noteOrder,
                        onOrderChange = {
                            viewModel.onEvent(NotesEvent.Order(it))
                        }
                    )
                }
                Spacer(modifier = Modifier.height(generalMargin))
                NoteList(
                    notes = state.notes,
                    emptyMessage = stringResource(id = R.string.notes_empty_list_message),
                    showGridView = state.isGridListSelected,
                    showDeleteButton = true,
                    onNoteClicked = {
                        navController.safeNavigate(
                            Screen.AddEditNoteScreen.route +
                                    "?$NOTE_ID_ARG=${it.id}&$NOTE_COLOR_ARG=${it.color}"
                        )
                    },
                    onDeleteClicked = {
                        viewModel.onEvent(NotesEvent.ClickDeleteNote(it))
                    }
                )
                if (state.showDeleteConfirmationDialog) {
                    ConfirmationDialog(message = stringResource(id = R.string.notes_delete_confirmation_message),
                        onConfirm = { viewModel.onEvent(NotesEvent.ConfirmDeleteNote) },
                        onDismiss = { viewModel.onEvent(NotesEvent.ShowConfirmDeleteNoteDialog(false)) })
                }
            }
        }
    )
}




