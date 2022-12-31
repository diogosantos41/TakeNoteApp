package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.domain.model.Note

class SearchNotesUseCase {
    operator fun invoke(queryText: String, notes: List<Note>): List<Note> {
        return notes.filter {
            it.title.contains(
                queryText, ignoreCase = true
            ) ||
                it.content.contains(
                    queryText, ignoreCase = true
                )
        }
    }
}