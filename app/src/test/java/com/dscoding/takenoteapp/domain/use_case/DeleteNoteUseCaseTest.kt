package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.data.repository.FakeNoteRepository
import com.dscoding.takenoteapp.domain.model.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteNoteUseCaseTest {

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var deleteNote: DeleteNoteUseCase
    private lateinit var note: Note

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        deleteNote = DeleteNoteUseCase(fakeNoteRepository)

        note = Note(
            title = "title",
            content = "content",
            createdTime = 1,
            editedTime = 1,
            color = 1
        )

        runBlocking {
            fakeNoteRepository.insertNote(note)
        }
    }

    @Test
    fun `Delete note, note deleted`() = runBlocking {
        deleteNote(note)
        val notes = fakeNoteRepository.getNotes().first()

        assertThat(notes.isEmpty()).isTrue()
    }
}