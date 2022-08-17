package com.ali.aamer.mynotes.features.feature_notes.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.core.utils.Status
import com.ali.aamer.mynotes.features.feature_notes.domain.repositories.INoteRepository
import com.ali.aamer.notesapp.models.notes.NoteRequestModel
import javax.inject.Inject

class DeleteNote @Inject constructor(private val iNoteRepository: INoteRepository) {

    private var _noteDeletionStatus = MutableLiveData<Resource<Boolean>>()
    val noteDeletionStatus: LiveData<Resource<Boolean>> get() = _noteDeletionStatus

    suspend operator fun invoke(noteRequestModel: NoteRequestModel) {
        _noteDeletionStatus.postValue(Resource.Loading(status = Status.LOADING))
        val response = iNoteRepository.deleteNote(noteRequestModel)
        _noteDeletionStatus.postValue(response)
    }
}