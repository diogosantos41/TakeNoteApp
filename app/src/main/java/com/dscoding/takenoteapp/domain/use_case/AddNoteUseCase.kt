package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.common.Failure
import com.dscoding.takenoteapp.common.Resource
import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.domain.repository.NoteRepository

class AddNoteUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(note: Note): Resource<Any?> {
        if (note.title.isBlank()) {
            return Resource.Error(Failure.EmptyNoteTitle)
        }
        if (note.content.isBlank()) {
            return Resource.Error(Failure.EmptyNoteContent)
        }
        repository.insertNote(note)
        return Resource.Success(null)
    }
}