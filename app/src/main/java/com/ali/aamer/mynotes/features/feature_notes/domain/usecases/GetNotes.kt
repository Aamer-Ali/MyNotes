package com.ali.aamer.mynotes.features.feature_notes.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.core.utils.Status
import com.ali.aamer.mynotes.features.feature_notes.domain.repositories.INoteRepository
import com.ali.aamer.notesapp.models.notes.NoteResponseModel
import javax.inject.Inject

class GetNotes @Inject constructor(private val iNoteRepository: INoteRepository) {

    private var _notesList = MutableLiveData<Resource<List<NoteResponseModel>>>()
    val notesListLiveData: LiveData<Resource<List<NoteResponseModel>>> get() = _notesList

    suspend operator fun invoke() {
        _notesList.postValue(Resource.Loading(status = Status.LOADING))
        val response = iNoteRepository.getNotes()
        _notesList.postValue(response)
    }

}