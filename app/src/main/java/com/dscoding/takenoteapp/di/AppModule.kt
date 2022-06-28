package com.dscoding.takenoteapp.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.dscoding.takenoteapp.data.data_source.NoteDatabase
import com.dscoding.takenoteapp.data.data_source.PreferencesDataStore
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(appContext, DATASTORE_NAME)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(DATASTORE_NAME) }
        )
    }

    @Provides
    @Singleton
    fun providePreferencesRepository(dataStore: PreferencesDataStore): PreferencesRepository {
        return PreferencesRepositoryImpl(dataStore)
    }

    @Provides
    @Singleton
    fun providePreferencesUseCases(repository: PreferencesRepository): PreferencesUseCases {
        return PreferencesUseCases(
            getUserPreference = GetUserPreferenceUseCase(repository),
            updateUserPreference = UpdateUserPreferenceUseCase(repository)
        )
    }
}