package com.ali.aamer.mynotes.features.feature_notes.data.data_sources.remote

import com.ali.aamer.notesapp.models.notes.NoteRequestModel
import com.ali.aamer.notesapp.models.notes.NoteResponseModel
import retrofit2.Response
import retrofit2.http.*

interface NotesAPI {
    @GET("/notes")
    suspend fun getNotes(): Response<List<NoteResponseModel>>

    @POST("/notes")
    suspend fun createNote(@Body noteRequest: NoteRequestModel): Response<NoteResponseModel>

    @PUT("/notes/{noteId}")
    suspend fun updateNote(
        @Path("noteId") noteId: String,
        @Body noteRequest: NoteRequestModel
    ): Response<NoteResponseModel>

    @DELETE("/notes/{noteId}")
    suspend fun deleteNote(@Path("noteId") noteId: String): Response<NoteResponseModel>
}