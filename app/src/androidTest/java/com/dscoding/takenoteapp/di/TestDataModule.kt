package com.dscoding.takenoteapp.di

import android.app.Application
import androidx.room.Room
import com.dscoding.takenoteapp.data.data_source.NoteDatabase
import com.dscoding.takenoteapp.data.data_store.FakeSettingsDataStore
import com.dscoding.takenoteapp.data.repository.NoteRepositoryImpl
import com.dscoding.takenoteapp.domain.data_store.SettingsDataStore
import com.dscoding.takenoteapp.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object TestDataModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.inMemoryDatabaseBuilder(app, NoteDatabase::class.java)
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideSettingsDataStore(): SettingsDataStore {
        return FakeSettingsDataStore()
    }
}