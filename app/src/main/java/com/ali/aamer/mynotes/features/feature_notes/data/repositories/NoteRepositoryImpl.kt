package com.ali.aamer.mynotes.features.feature_notes.data.repositories

import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.core.utils.Status
import com.ali.aamer.mynotes.features.feature_notes.data.data_sources.local.NotesDao
import com.ali.aamer.mynotes.features.feature_notes.data.data_sources.remote.NotesAPI
import com.ali.aamer.mynotes.features.feature_notes.domain.repositories.INoteRepository
import com.ali.aamer.notesapp.models.notes.NoteRequestModel
import com.ali.aamer.notesapp.models.notes.NoteResponseModel
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val notesAPI: NotesAPI,
    private val notesDao: NotesDao
) : INoteRepository {
    override suspend fun getNotes(): Resource<List<NoteResponseModel>> {
        val response = notesAPI.getNotes()
        return if (response.isSuccessful && response.body() != null) {
            response.body()!!.forEach { note ->
                notesDao.createNote(note)
            }
            Resource.Success(status = Status.SUCCESS, data = response.body())
        } else {
            Resource.Failure(
                status = Status.FAILURE,
                message = "There is some problem while fetching the data"
            )
        }
        //check if the network is connected
        //if yes then get the data from the remote server and add that data to local database
        //get the list of notes from local database and then return that
        //else
        //get the list of notes from local database and then return that
    }


    override suspend fun createNote(noteRequestModel: NoteRequestModel): Resource<Boolean> {
        //check if the network is connected
        //if yes then add data to database and then sync data with remote server
        //get the response form API
        //if the response is success then
        //update database and return success
        return Resource.Success(status = Status.SUCCESS, data = true)

    }

    override suspend fun updateNote(noteRequestModel: NoteRequestModel): Resource<Boolean> {
        //check if the network is connected
        //if yes then update data to database ( set the value ) and then sync data with remote server
        //get the response form API
        //if the response is success then
        //update database and return success
        return Resource.Success(status = Status.SUCCESS, data = true)
    }

    override suspend fun getNoteById(id: String): Resource<NoteResponseModel> {
        //get the note form server by id
        return Resource.Success(status = Status.SUCCESS, data = null)
    }

    override suspend fun deleteNote(noteRequestModel: NoteRequestModel): Resource<Boolean> {
        //check if the network is connected
        //if yes then remove data from database ( set the value to 1 ) and then sync data with remote server
        //get the response form API
        //if the response is success then
        //update database and return success
        return Resource.Success(status = Status.SUCCESS, data = true)
    }
}