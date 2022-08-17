package com.ali.aamer.mynotes.features.feature_notes.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.core.utils.Status
import com.ali.aamer.mynotes.features.feature_notes.domain.repositories.INoteRepository
import com.ali.aamer.notesapp.models.notes.NoteRequestModel
import javax.inject.Inject

class UpdateNote @Inject constructor(private val iNoteRepository: INoteRepository) {

    private var _noteUpdateStatus = MutableLiveData<Resource<Boolean>>()
    val noteUpdateStatus: LiveData<Resource<Boolean>> get() = _noteUpdateStatus

    suspend operator fun invoke(noteRequestModel: NoteRequestModel) {
        _noteUpdateStatus.postValue(Resource.Loading(status = Status.LOADING))
        val response = iNoteRepository.updateNote(noteRequestModel)
        _noteUpdateStatus.postValue(response)
    }

}