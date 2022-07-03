package com.dscoding.takenoteapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dscoding.takenoteapp.domain.model.Label
import com.dscoding.takenoteapp.domain.model.Note

@Database(entities = [Note::class, Label::class], version = 1)

abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}