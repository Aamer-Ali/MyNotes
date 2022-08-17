package com.ali.aamer.mynotes.features.feature_notes.data.data_sources.local

import androidx.room.*
import com.ali.aamer.notesapp.models.notes.NoteRequestModel
import com.ali.aamer.notesapp.models.notes.NoteResponseModel
import retrofit2.http.DELETE

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createNote(noteResponseModel: NoteResponseModel)

    @Update
    suspend fun updateNote(noteResponseModel: NoteResponseModel)

    @Delete
    suspend fun deleteNote(noteResponseModel: NoteResponseModel)

    @Query("SELECT * FROM tbl_notes")
    suspend fun getNotes(): List<NoteRequestModel>

    @Query("SELECT * FROM tbl_notes WHERE _id = :id")
    suspend fun getNoteById(id: String): NoteRequestModel
}