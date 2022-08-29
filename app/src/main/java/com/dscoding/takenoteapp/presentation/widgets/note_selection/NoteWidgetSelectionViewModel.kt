package com.dscoding.takenoteapp.presentation.widgets.note_selection

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NoteWidgetSelectionViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(NoteWidgetSelectionState())
    val state: State<NoteWidgetSelectionState> = _state

    private var getNotesJob: Job? = null

    private var unfilteredNotesList: List<Note> = emptyList()

    init {
        getNotes()
    }

    fun onEvent(event: NoteWidgetSelectionEvent) {
        when (event) {
            is NoteWidgetSelectionEvent.CleanSearchText -> {
                _state.value = state.value.copy(
                    searchText = "",
                    notes = unfilteredNotesList
                )
            }
            is NoteWidgetSelectionEvent.EnteredSearchText -> {
                _state.value = state.value.copy(
                    notes = noteUseCases.searchNotes(event.value, unfilteredNotesList),
                    searchText = event.value,
                )
            }
        }
    }

    private fun getNotes() {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes()
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                )
                unfilteredNotesList = notes
            }
            .launchIn(viewModelScope)
    }
}