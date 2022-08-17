package com.ali.aamer.mynotes.features.feature_notes.data.data_sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ali.aamer.notesapp.models.notes.NoteResponseModel

@Database(entities = [NoteResponseModel::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}