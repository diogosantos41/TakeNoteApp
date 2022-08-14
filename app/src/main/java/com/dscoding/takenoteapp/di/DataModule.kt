package com.dscoding.takenoteapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.dscoding.takenoteapp.data.data_source.NoteDatabase
import com.dscoding.takenoteapp.data.repository.NoteRepositoryImpl
import com.dscoding.takenoteapp.data.data_store.SettingsDataStoreImpl
import com.dscoding.takenoteapp.domain.repository.NoteRepository
import com.dscoding.takenoteapp.domain.data_store.SettingsDataStore
import com.dscoding.takenoteapp.domain.use_case.*
import com.dscoding.takenoteapp.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

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
    fun provideSettingsDataStore(@ApplicationContext context: Context): SettingsDataStore {
        return SettingsDataStoreImpl(context)
    }
}