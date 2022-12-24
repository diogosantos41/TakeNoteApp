package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.common.Failure
import com.dscoding.takenoteapp.common.Result
import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.domain.repository.NoteRepository

class AddNoteUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(note: Note): Result<Nothing> {
        if (note.title.isBlank()) {
            return Result.Error(Failure.EmptyNoteTitle)
        }
        if (note.content.isBlank()) {
            return Result.Error(Failure.EmptyNoteContent)
        }
        repository.insertNote(note)
        return Result.Success()
    }
}