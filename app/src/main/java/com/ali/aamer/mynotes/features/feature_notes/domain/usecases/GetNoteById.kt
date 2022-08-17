package com.ali.aamer.mynotes.features.feature_notes.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.core.utils.Status
import com.ali.aamer.mynotes.features.feature_notes.domain.repositories.INoteRepository
import com.ali.aamer.notesapp.models.notes.NoteResponseModel
import javax.inject.Inject

class GetNoteById @Inject constructor(private val iNoteRepository: INoteRepository) {

    private var _noteStatus = MutableLiveData<Resource<NoteResponseModel>>()
    val noteStatus: LiveData<Resource<NoteResponseModel>> get() = _noteStatus

    suspend operator fun invoke(id: String) {
        _noteStatus.postValue(Resource.Loading(status = Status.LOADING))
        val response = iNoteRepository.getNoteById(id)
        _noteStatus.postValue(response)
    }
}