package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.Result
import com.dscoding.takenoteapp.common.StringResource
import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.domain.repository.NoteRepository

class AddNoteUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(note: Note): Result<Nothing> {
        if (note.title.isBlank()) {
            return Result.Error(errorMessage = StringResource(R.string.error_add_note_empty_title))
        }
        repository.insertNote(note)
        return Result.Success()
    }
}