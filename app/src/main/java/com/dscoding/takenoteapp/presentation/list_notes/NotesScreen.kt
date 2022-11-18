package com.dscoding.takenoteapp.presentation.list_notes

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.TestTags
import com.dscoding.takenoteapp.presentation.common.ConfirmationDialog
import com.dscoding.takenoteapp.presentation.common.NoteList
import com.dscoding.takenoteapp.presentation.list_notes.components.GreetingSection
import com.dscoding.takenoteapp.presentation.list_notes.components.OrderSection
import com.dscoding.takenoteapp.presentation.util.Screen
import com.dscoding.takenoteapp.utils.extensions.safeNavigate

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

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    navController.safeNavigate(Screen.AddEditNoteScreen.route)
                }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.notes_content_description_add),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colorScheme.primary,
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                )
            ) {
                IconButton(
                    onClick = { navController.safeNavigate(Screen.SearchNotesScreen.route) },
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.notes_content_description_search),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(
                    onClick = {
                        viewModel.onEvent(NotesEvent.ToggleGridList)
                    },
                ) {
                    Icon(
                        imageVector = if (state.isGridListSelected) Icons.Default.List else Icons.Default.GridView,
                        contentDescription = stringResource(id = R.string.notes_content_description_toggle_grid_list_view),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(
                    onClick = { navController.safeNavigate(Screen.SettingsScreen.route) },
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = stringResource(id = R.string.notes_content_description_settings),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        scaffoldState = scaffoldState,
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
            ) {
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
                    GreetingSection(visible = state.isGreetingSectionVisible)
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.notes_list_title),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        IconButton(
                            onClick = {
                                viewModel.onEvent(NotesEvent.ToggleOrderSection)
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Sort,
                                contentDescription = stringResource(id = R.string.notes_content_description_sort),
                                tint = MaterialTheme.colorScheme.onBackground
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
                                .padding(vertical = generalMargin)
                                .testTag(TestTags.ORDER_SECTION),
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
                        showGridView = state.isGridListSelected && state.notes.size > 1,
                        showDeleteButton = true,
                        onNoteClicked = {
                            navController.safeNavigate(
                                Screen.AddEditNoteScreen.withArgs(
                                    noteId = "${it.id}",
                                    noteColor = "${it.color}"
                                )
                            )
                        },
                        onDeleteClicked = {
                            viewModel.onEvent(NotesEvent.ClickDeleteNote(it))
                        }
                    )
                    ConfirmationDialog(
                        visible = state.showDeleteConfirmationDialog,
                        message = stringResource(id = R.string.notes_delete_confirmation_message),
                        onConfirm = { viewModel.onEvent(NotesEvent.ConfirmDeleteNote) },
                        onDismiss = {
                            viewModel.onEvent(
                                NotesEvent.ShowConfirmDeleteNoteDialog(
                                    false
                                )
                            )
                        })
                }
            }
        }
    )
}




