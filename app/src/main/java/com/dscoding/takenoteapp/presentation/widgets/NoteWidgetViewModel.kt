package com.dscoding.takenoteapp.presentation.widgets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dscoding.takenoteapp.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteWidgetViewModel @Inject constructor(
    private val noteUseCase: NoteUseCases,
) : ViewModel() {

    fun getNote(id: Int) {
        viewModelScope.launch {
            noteUseCase.getNote(id)
        }
    }
}