package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.domain.repository.NoteRepository
import com.dscoding.takenoteapp.utils.Failure
import com.dscoding.takenoteapp.utils.Resource

class AddNoteUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(note: Note): Resource<Any?> {
        if (note.title.isBlank()) {
            return Resource.Error(Failure.EmptyNoteTitle)
        }
        if (note.content.isBlank()) {
            return Resource.Error(Failure.EmptyNoteTitle)
        }
        repository.insertNote(note)
        return Resource.Success(null)
    }
}