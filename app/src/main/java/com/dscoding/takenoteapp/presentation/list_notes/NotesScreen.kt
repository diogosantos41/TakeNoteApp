package com.dscoding.takenoteapp.presentation.list_notes

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.presentation.common.SnackbarHostController
import com.dscoding.takenoteapp.presentation.list_notes.components.EmptyListAlert
import com.dscoding.takenoteapp.presentation.list_notes.components.GreetingSection
import com.dscoding.takenoteapp.presentation.list_notes.components.NoteItem
import com.dscoding.takenoteapp.presentation.list_notes.components.OrderSection
import com.dscoding.takenoteapp.presentation.util.Screen
import com.dscoding.takenoteapp.ui.theme.ThemeManager
import com.dscoding.takenoteapp.utils.Constants.NOTE_COLOR_ARG
import com.dscoding.takenoteapp.utils.Constants.NOTE_ID_ARG
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val generalMargin = dimensionResource(R.dimen.general_margin)

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
                    navController.navigate(Screen.AddEditNoteScreen.route)
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
                    onClick = { },
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
                        contentDescription = "Toggle Grid/List View",
                        tint = ThemeManager.colors.iconColor
                    )
                }
                IconButton(
                    onClick = { navController.navigate(Screen.LabelsScreen.route) },
                ) {
                    Icon(
                        imageVector = Icons.Default.Label,
                        contentDescription = "Label",
                        tint = ThemeManager.colors.iconColor
                    )
                }
                IconButton(
                    onClick = { navController.navigate(Screen.SettingsScreen.route) },
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
                            contentDescription = "Sort",
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
                        modifier = Modifier.fillMaxWidth(),
                        noteOrder = state.noteOrder,
                        onOrderChange = {
                            viewModel.onEvent(NotesEvent.Order(it))
                        }
                    )
                }
                Spacer(modifier = Modifier.height(generalMargin))
                if (state.notes.isEmpty()) {
                    EmptyListAlert(
                        stringResource(id = R.string.notes_empty_list_message)
                    )
                } else {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Fixed(if (state.isGridListSelected) 2 else 1),
                        verticalArrangement = Arrangement.spacedBy(generalMargin),
                        horizontalArrangement = Arrangement.spacedBy(generalMargin)
                    ) {
                        itemsIndexed(state.notes) { index, note ->
                            NoteItem(
                                note = note,
                                isLastItem = index == state.notes.size - 1,
                                modifier = Modifier
                                    .animateItemPlacement(
                                        animationSpec = tween(
                                            durationMillis = 300
                                        )
                                    )
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate(
                                            Screen.AddEditNoteScreen.route +
                                                    "?$NOTE_ID_ARG=${note.id}&$NOTE_COLOR_ARG=${note.color}"
                                        )
                                    },
                                onDeleteClick = {
                                    viewModel.onEvent(NotesEvent.DeleteNote(note))
                                    scope.launch {
                                        val result = scaffoldState.snackbarHostState.showSnackbar(
                                            message = "Note deleted",
                                            actionLabel = "Undo"
                                        )
                                        if (result == SnackbarResult.ActionPerformed) {
                                            viewModel.onEvent(NotesEvent.RestoreNote)
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}




