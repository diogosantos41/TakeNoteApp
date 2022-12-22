package com.dscoding.takenoteapp.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.Constants.NOTE_ID_ARG
import com.dscoding.takenoteapp.common.Constants.NOTE_INVALID_ID
import com.dscoding.takenoteapp.common.Failure
import com.dscoding.takenoteapp.common.Resource
import com.dscoding.takenoteapp.common.UiText
import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.domain.use_case.NoteUseCases
import com.dscoding.takenoteapp.domain.use_case.PreferencesUseCases
import com.dscoding.takenoteapp.utils.DateUtils
import com.dscoding.takenoteapp.utils.extensions.logAddNote
import com.dscoding.takenoteapp.utils.extensions.logDeleteNote
import com.dscoding.takenoteapp.utils.extensions.logEditNote
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    private val preferencesUseCases: PreferencesUseCases,
    saveStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(
        NoteTextFieldState(
            hint = UiText.StringResource(resId = R.string.add_edit_note_title_hint)
        )
    )
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(
        NoteTextFieldState(
            hint = UiText.StringResource(resId = R.string.add_edit_note_content_hint)
        )
    )
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _state = mutableStateOf(
        AddEditNoteState(
            pageTitle = UiText.StringResource(R.string.add_edit_note_add_title),
            noteColor = Note.noteColors.random().toArgb(),
            isEditingNote = false,
            lastTimeEdited = ""
        )
    )

    val state: State<AddEditNoteState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentSelectedNote: Note? = null

    private var dateFormat: String = DateUtils.TWENTY_FOUR_HOUR_DATE_FORMAT

    private var getPreferencesJob: Job? = null

    init {
        getPreferences()
        saveStateHandle.get<Int>(NOTE_ID_ARG)?.let { noteId ->
            if (noteId != NOTE_INVALID_ID) {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.let { note ->
                        currentSelectedNote = note
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = _noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _state.value = state.value.copy(
                            pageTitle = UiText.StringResource(R.string.add_edit_note_edit_title),
                            noteColor = note.color,
                            isEditingNote = true,
                            lastTimeEdited = DateUtils.convertTimeMillisToStringDate(
                                timeMillis = note.editedTime,
                                dateFormat
                            )
                        )
                    } ?: _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            message = UiText.StringResource(R.string.error_add_note_invalid_note)
                        )
                    )
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                        noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                        noteContent.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeColor -> {
                _state.value = state.value.copy(
                    noteColor = event.color
                )
            }
            is AddEditNoteEvent.SaveNote -> {
                if (state.value.isEditingNote) {
                    Firebase.analytics.logEditNote()
                } else {
                    Firebase.analytics.logAddNote(state.value.noteColor.toString())
                }
                var addNoteResult: Resource<Any?>
                viewModelScope.launch {
                    addNoteResult = noteUseCases.addNote(
                        Note(
                            title = noteTitle.value.text,
                            content = noteContent.value.text.trim(),
                            createdTime = if (state.value.isEditingNote) currentSelectedNote?.createdTime
                            else System.currentTimeMillis(),
                            editedTime = System.currentTimeMillis(),
                            color = state.value.noteColor,
                            id = currentSelectedNote?.id
                        )
                    )
                    when (addNoteResult) {
                        is Resource.Success -> {
                            _eventFlow.emit(UiEvent.SaveNote)
                        }
                        is Resource.Error -> {
                            val errorMessage = when (addNoteResult.failure) {
                                is Failure.EmptyNoteTitle -> {
                                    UiText.StringResource(R.string.error_add_note_empty_title)
                                }
                                is Failure.EmptyNoteContent -> {
                                    UiText.StringResource(R.string.error_add_note_empty_content)
                                }
                                else -> {
                                    UiText.StringResource(R.string.error_unknown)
                                }
                            }
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    message = errorMessage
                                )
                            )
                        }
                    }
                }
            }
            is AddEditNoteEvent.ClickDeleteNote -> {
                _state.value = state.value.copy(
                    showDeleteConfirmationDialog = true
                )
            }
            is AddEditNoteEvent.ConfirmDeleteNote -> {
                Firebase.analytics.logDeleteNote()
                viewModelScope.launch {
                    currentSelectedNote?.let { noteUseCases.deleteNote(it) }
                    _eventFlow.emit(UiEvent.DeleteNote)
                }
            }
            is AddEditNoteEvent.ShowConfirmDeleteNoteDialog -> {
                _state.value = state.value.copy(
                    showDeleteConfirmationDialog = event.toShowDialog
                )
            }
        }
    }

    private fun getPreferences() {
        getPreferencesJob?.cancel()
        getPreferencesJob = preferencesUseCases.getPreferences()
            .onEach { preferences ->
                dateFormat = if (preferences.twentyFourHourClock) {
                    DateUtils.TWENTY_FOUR_HOUR_DATE_FORMAT
                } else {
                    DateUtils.THIRTEEN_HOUR_DATE_FORMAT
                }
            }
            .launchIn(viewModelScope)
    }

    sealed interface UiEvent {
        data class ShowSnackbar(val message: UiText) : UiEvent
        object SaveNote : UiEvent
        object DeleteNote : UiEvent
    }
}