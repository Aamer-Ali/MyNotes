package com.ali.aamer.mynotes.features.feature_notes.data.repositories

import androidx.lifecycle.MutableLiveData
import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.core.utils.Status
import com.ali.aamer.mynotes.features.feature_notes.domain.repositories.INoteRepository
import com.ali.aamer.notesapp.models.notes.NoteResponseModel
import org.junit.Assert.*

class FakeNoteRepositoryImpl : INoteRepository {

    private val notes = mutableListOf<NoteResponseModel>()
    private val observeNotesList = MutableLiveData<Resource<List<NoteResponseModel>>>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getNotes(): Resource<List<NoteResponseModel>> {
        return if (shouldReturnNetworkError) {
            //Check if the database contains the notes

            //if yes then return success

            //else
            Resource.Failure(status = Status.FAILURE, message = "There is an Network Error")
        } else {
            Resource.Success(status = Status.SUCCESS, data = emptyList())
        }
    }


}