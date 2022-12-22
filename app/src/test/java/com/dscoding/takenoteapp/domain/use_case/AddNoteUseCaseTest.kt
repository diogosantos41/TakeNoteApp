package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.common.Failure
import com.dscoding.takenoteapp.data.repository.FakeNoteRepository
import com.dscoding.takenoteapp.domain.model.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddNoteUseCaseTest {

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var addNote: AddNoteUseCase
    private lateinit var noteWithoutTitle: Note
    private lateinit var noteWithoutContent: Note
    private lateinit var noteWithTitleAndContent: Note

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        addNote = AddNoteUseCase(fakeNoteRepository)

        noteWithoutTitle = Note(
            title = "",
            content = "content",
            createdTime = 1,
            editedTime = 1,
            color = 1
        )

        noteWithoutContent = Note(
            title = "title",
            content = "",
            createdTime = 1,
            editedTime = 1,
            color = 1
        )

        noteWithTitleAndContent = Note(
            title = "title",
            content = "content",
            createdTime = 1,
            editedTime = 1,
            color = 1
        )
    }

    @Test
    fun `Add note without title, note not added and error caught`() = runBlocking {
        val addNoteResult = addNote(noteWithoutTitle)
        val notes = fakeNoteRepository.getNotes().first()

        assertThat(notes.isEmpty()).isTrue()
        assertThat(addNoteResult.failure is Failure.EmptyNoteTitle).isTrue()
    }

    @Test
    fun `Add note without content, note not added and error caught`() = runBlocking {
        val addNoteResult = addNote(noteWithoutContent)
        val notes = fakeNoteRepository.getNotes().first()

        assertThat(notes.isEmpty()).isTrue()
        assertThat(addNoteResult.failure is Failure.EmptyNoteContent).isTrue()
    }

    @Test
    fun `Add note, note added`() = runBlocking {
        addNote(noteWithTitleAndContent)
        val notes = fakeNoteRepository.getNotes().first()

        assertThat(noteWithTitleAndContent).isEqualTo(notes[0])
    }
}