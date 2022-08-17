package com.ali.aamer.mynotes.features.feature_notes.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.core.utils.Status
import com.ali.aamer.mynotes.features.feature_notes.domain.repositories.INoteRepository
import com.ali.aamer.notesapp.models.notes.NoteRequestModel
import javax.inject.Inject

class CreateNote @Inject constructor(private val iNoteRepository: INoteRepository) {

    private var _noteCreationStatus = MutableLiveData<Resource<Boolean>>()
    val noteCreationStatus: LiveData<Resource<Boolean>> get() = _noteCreationStatus

    suspend operator fun invoke(noteRequestModel: NoteRequestModel) {
        _noteCreationStatus.postValue(Resource.Loading(status = Status.LOADING))
        val response = iNoteRepository.createNote(noteRequestModel)
        _noteCreationStatus.postValue(response)
    }

}