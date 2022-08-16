package com.ali.aamer.mynotes.features.feature_notes.data.repositories

import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.core.utils.Status
import com.ali.aamer.mynotes.features.feature_notes.domain.repositories.INoteRepository
import com.ali.aamer.notesapp.models.notes.NoteResponseModel
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor() : INoteRepository {
    override suspend fun getNotes(): Resource<List<NoteResponseModel>> {
        return Resource.Success(status = Status.SUCCESS, data = emptyList())
    }
}