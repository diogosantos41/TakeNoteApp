package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.data.repository.FakeNoteRepository
import com.dscoding.takenoteapp.domain.model.Note
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SearchNotesUseCaseTest {

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var searchNotes: SearchNotesUseCase

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        searchNotes = SearchNotesUseCase()

        val notesToInsert = mutableListOf<Note>()
        ('a'..'z').forEachIndexed { index, char ->
            notesToInsert.add(
                Note(
                    title = char.toString(),
                    content = char.toString(),
                    createdTime = index.toLong(),
                    editedTime = index.toLong(),
                    color = index,
                )
            )
        }

        runBlocking {
            notesToInsert.forEach { fakeNoteRepository.insertNote(it) }
        }
    }

    @Test
    fun `Search notes by title, correct queried list`() = runBlocking {
        val notes = fakeNoteRepository.getNotes().first()
        val letter = ('a'..'z').random()
        val filteredList = searchNotes(letter.toString(), notes)
        Truth.assertThat(filteredList.size).isEqualTo(1)
        Truth.assertThat(filteredList[0].title).isEqualTo(letter.toString())
    }

    @Test
    fun `Search notes by content, correct queried list`() = runBlocking {
        val notes = fakeNoteRepository.getNotes().first()
        val letter = ('a'..'z').random()
        val filteredList = searchNotes(letter.toString(), notes)
        Truth.assertThat(filteredList.size).isEqualTo(1)
        Truth.assertThat(filteredList[0].content).isEqualTo(letter.toString())
    }
}