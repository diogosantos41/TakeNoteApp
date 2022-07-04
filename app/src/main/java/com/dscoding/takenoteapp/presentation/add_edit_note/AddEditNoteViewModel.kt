package com.dscoding.takenoteapp.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.domain.use_case.NoteUseCases
import com.dscoding.takenoteapp.utils.Constants.NOTE_ID_ARG
import com.dscoding.takenoteapp.utils.DateUtils
import com.dscoding.takenoteapp.utils.Failure
import com.dscoding.takenoteapp.utils.Resource
import com.dscoding.takenoteapp.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
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

    init {
        saveStateHandle.get<Int>(NOTE_ID_ARG)?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note ->
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
                            lastTimeEdited = DateUtils.convertTimeMillisToStringDate(timeMillis = note.editedTime)
                        )
                    }
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
                var addNoteResult: Resource<Any?>
                viewModelScope.launch {
                    addNoteResult = noteUseCases.addNote(
                        Note(
                            title = noteTitle.value.text,
                            content = noteContent.value.text,
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
                            var errorMessage = when (addNoteResult.failure) {
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
            is AddEditNoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    currentSelectedNote?.let { noteUseCases.deleteNote(it) }
                    _eventFlow.emit(UiEvent.DeleteNote)
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: UiText) : UiEvent()
        object SaveNote : UiEvent()
        object DeleteNote : UiEvent()
    }
}