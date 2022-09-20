package com.dscoding.takenoteapp.presentation.add_edit_note

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.presentation.add_edit_note.components.TransparentHintTextField
import com.dscoding.takenoteapp.presentation.common.ConfirmationDialog
import com.dscoding.takenoteapp.presentation.common.SnackbarHostController
import com.dscoding.takenoteapp.ui.theme.DarkerGrey
import com.dscoding.takenoteapp.ui.theme.ThemeManager
import com.dscoding.takenoteapp.ui.theme.White
import com.dscoding.takenoteapp.utils.Constants.NOTE_INVALID_COLOR
import com.dscoding.takenoteapp.utils.extensions.popBackToDashboard
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(if (noteColor != NOTE_INVALID_COLOR) noteColor else state.noteColor)
        )
    }
    val scope = rememberCoroutineScope()

    val generalMargin = dimensionResource(R.dimen.margin)


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditNoteViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                is AddEditNoteViewModel.UiEvent.SaveNote -> {
                    navController.popBackToDashboard()
                }
                is AddEditNoteViewModel.UiEvent.DeleteNote -> {
                    navController.popBackToDashboard()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = state.pageTitle.asString(),
                        color = White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackToDashboard()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.add_edit_note_content_description_back),
                            tint = White
                        )
                    }
                },
                backgroundColor = ThemeManager.colors.toolbarColor,
                elevation = 0.dp
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = ThemeManager.colors.mainColor,
                onClick = {
                    viewModel.onEvent(AddEditNoteEvent.SaveNote)
                }) {
                if (state.isEditingNote) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = stringResource(id = R.string.add_edit_note_content_description_edit),
                        tint = ThemeManager.colors.iconColor
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = stringResource(id = R.string.add_edit_note_content_description_save),
                        tint = ThemeManager.colors.iconColor
                    )
                }
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
                if (state.isEditingNote) {
                    IconButton(
                        onClick = { viewModel.onEvent(AddEditNoteEvent.ClickDeleteNote) },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(id = R.string.add_edit_note_content_description_delete),
                            tint = ThemeManager.colors.iconColor
                        )
                    }
                }
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = { SnackbarHostController(it) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(noteBackgroundAnimatable.value)
                    .padding(
                        generalMargin,
                        generalMargin,
                        generalMargin,
                        padding.calculateBottomPadding()
                    )
            ) {
                if (state.isEditingNote) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(
                            id = R.string.add_edit_note_edited_on,
                            state.lastTimeEdited
                        ),
                        style = MaterialTheme.typography.body1,
                        color = DarkerGrey,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(generalMargin))
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.add_note_color_row_padding)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Note.noteColors.forEach { color ->
                        val colorInt = color.toArgb()
                        Box(
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen.add_note_color_circle_size))
                                .shadow(
                                    dimensionResource(id = R.dimen.add_note_color_circle_shadow_size),
                                    CircleShape
                                )
                                .clip(CircleShape)
                                .background(color)
                                .border(
                                    width = dimensionResource(id = R.dimen.add_note_color_circle_selected_border_size),
                                    color = if (state.noteColor == colorInt) {
                                        DarkerGrey
                                    } else Color.Transparent,
                                    shape = CircleShape
                                )
                                .clickable {
                                    scope.launch {
                                        noteBackgroundAnimatable.animateTo(
                                            targetValue = Color(colorInt),
                                            animationSpec = tween(
                                                durationMillis = 500
                                            )
                                        )
                                    }
                                    viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                                }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(generalMargin))
                TransparentHintTextField(
                    text = titleState.text,
                    hint = titleState.hint.asString(),
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                    },
                    isHintVisible = titleState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(generalMargin))
                TransparentHintTextField(
                    text = contentState.text,
                    hint = contentState.hint.asString(),
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                    },
                    isHintVisible = contentState.isHintVisible,
                    singleLine = false,
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxHeight()
                )
                if (state.showDeleteConfirmationDialog) {
                    ConfirmationDialog(message = stringResource(id = R.string.notes_delete_confirmation_message),
                        onConfirm = { viewModel.onEvent(AddEditNoteEvent.ConfirmDeleteNote) },
                        onDismiss = {
                            viewModel.onEvent(
                                AddEditNoteEvent.ShowConfirmDeleteNoteDialog(
                                    false
                                )
                            )
                        })
                }
            }
        }
    )
}