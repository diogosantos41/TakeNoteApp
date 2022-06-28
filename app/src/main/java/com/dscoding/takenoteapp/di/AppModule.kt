package com.dscoding.takenoteapp.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.dscoding.takenoteapp.data.data_source.NoteDatabase
import com.dscoding.takenoteapp.data.repository.NoteRepositoryImpl
import com.dscoding.takenoteapp.data.repository.PreferencesRepositoryImpl
import com.dscoding.takenoteapp.domain.repository.NoteRepository
import com.dscoding.takenoteapp.domain.repository.PreferencesRepository
import com.dscoding.takenoteapp.domain.use_case.*
import com.dscoding.takenoteapp.utils.Constants.DATABASE_NAME
import com.dscoding.takenoteapp.utils.Constants.DATASTORE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(app, NoteDatabase::class.java, DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository),
        )
    }

    @Provides
    @Singleton
    fun providePreferencesRepository(@ApplicationContext context: Context): PreferencesRepository {
        return PreferencesRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun providePreferencesUseCases(repository: PreferencesRepository): PreferencesUseCases {
        return PreferencesUseCases(
            getUserPreference = GetPreferenceUseCase(repository),
            updateUserPreference = UpdatePreferenceUseCase(repository)
        )
    }
}