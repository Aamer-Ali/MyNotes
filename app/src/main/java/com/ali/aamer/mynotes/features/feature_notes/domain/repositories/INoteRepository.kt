package com.ali.aamer.mynotes.features.feature_notes.domain.repositories

import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.notesapp.models.notes.NoteRequestModel
import com.ali.aamer.notesapp.models.notes.NoteResponseModel

interface INoteRepository {
    suspend fun getNotes(): Resource<List<NoteResponseModel>>
    suspend fun createNote(noteRequestModel: NoteRequestModel): Resource<Boolean>
    suspend fun updateNote(noteRequestModel: NoteRequestModel): Resource<Boolean>
    suspend fun getNoteById(id: String): Resource<NoteResponseModel>
    suspend fun deleteNote(noteRequestModel: NoteRequestModel): Resource<Boolean>
}