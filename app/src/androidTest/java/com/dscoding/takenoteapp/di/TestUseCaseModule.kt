package com.dscoding.takenoteapp.di

import com.dscoding.takenoteapp.domain.data_store.SettingsDataStore
import com.dscoding.takenoteapp.domain.repository.NoteRepository
import com.dscoding.takenoteapp.domain.use_case.AddNoteUseCase
import com.dscoding.takenoteapp.domain.use_case.DeleteNoteUseCase
import com.dscoding.takenoteapp.domain.use_case.GetNoteUseCase
import com.dscoding.takenoteapp.domain.use_case.GetNotesUseCase
import com.dscoding.takenoteapp.domain.use_case.GetPreferencesUseCase
import com.dscoding.takenoteapp.domain.use_case.NoteUseCases
import com.dscoding.takenoteapp.domain.use_case.PreferencesUseCases
import com.dscoding.takenoteapp.domain.use_case.SearchNotesUseCase
import com.dscoding.takenoteapp.domain.use_case.UpdatePreferencesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object TestUseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository),
            searchNotes = SearchNotesUseCase()
        )
    }

    @Provides
    @ViewModelScoped
    fun providePreferencesUseCases(repository: SettingsDataStore): PreferencesUseCases {
        return PreferencesUseCases(
            getPreferences = GetPreferencesUseCase(repository),
            updatePreferences = UpdatePreferencesUseCase(repository)
        )
    }
}