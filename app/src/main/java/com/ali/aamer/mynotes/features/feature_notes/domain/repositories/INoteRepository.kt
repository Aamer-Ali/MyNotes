package com.ali.aamer.mynotes.features.feature_notes.domain.repositories

import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.notesapp.models.notes.NoteResponseModel

interface INoteRepository {
    suspend fun getNotes(): Resource<List<NoteResponseModel>>
}