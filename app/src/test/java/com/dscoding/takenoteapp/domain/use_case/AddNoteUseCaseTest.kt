package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.data.repository.FakeNoteRepository
import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.utils.Failure
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddNoteUseCaseTest {

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var addNote: AddNoteUseCase

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        addNote = AddNoteUseCase(fakeNoteRepository)
    }

    @Test
    fun `Add note without title, note not added and error caught`() = runBlocking {
        val note = Note(
            title = "",
            content = "content",
            createdTime = 1,
            editedTime = 1,
            color = 1
        )

        val addNoteResult = addNote(note)
        val notes = fakeNoteRepository.getNotes().first()

        assertThat(notes.isEmpty()).isTrue()
        assertThat(addNoteResult.failure is Failure.EmptyNoteTitle).isTrue()
    }

    @Test
    fun `Add note without content, note not added and error caught`() = runBlocking {
        val note = Note(
            title = "title",
            content = "",
            createdTime = 1,
            editedTime = 1,
            color = 1
        )

        val addNoteResult = addNote(note)
        val notes = fakeNoteRepository.getNotes().first()

        assertThat(notes.isEmpty()).isTrue()
        assertThat(addNoteResult.failure is Failure.EmptyNoteContent).isTrue()
    }

    @Test
    fun `Add note, note added`() = runBlocking {
        val note = Note(
            title = "title",
            content = "content",
            createdTime = 1,
            editedTime = 1,
            color = 1
        )

        addNote(note)
        val notes = fakeNoteRepository.getNotes().first()

        assertThat(note).isEqualTo(notes[0])
    }
}