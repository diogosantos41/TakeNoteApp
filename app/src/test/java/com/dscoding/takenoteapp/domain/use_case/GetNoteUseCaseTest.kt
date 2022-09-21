package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.data.repository.FakeNoteRepository
import com.dscoding.takenoteapp.domain.model.Note
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNoteUseCaseTest {

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var getNote: GetNoteUseCase
    private lateinit var note: Note

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        getNote = GetNoteUseCase(fakeNoteRepository)

        note = Note(
            id = 1,
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
    fun `Get note, note exists`() = runBlocking {
        val noteFromRepository = fakeNoteRepository.getNoteById(note.id!!)
        Truth.assertThat(note).isEqualTo(noteFromRepository)
    }
}