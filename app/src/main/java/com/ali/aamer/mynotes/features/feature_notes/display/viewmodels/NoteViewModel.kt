package com.ali.aamer.mynotes.features.feature_notes.display.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.aamer.mynotes.features.feature_notes.display.events.NoteEvents
import com.ali.aamer.mynotes.features.feature_notes.domain.usecases.NotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val notesUseCases: NotesUseCases) : ViewModel() {

    val noteCreationStatus = notesUseCases.createNote.noteCreationStatus
    val noteUpdateStatus = notesUseCases.updateNote.noteUpdateStatus
    val noteDeletionStatus = notesUseCases.deleteNote.noteDeletionStatus
    val noteList = notesUseCases.getNotes.notesListLiveData
    val noteStatus = notesUseCases.getNoteById.noteStatus

    fun notesOperation(noteEvents: NoteEvents) {
        when (noteEvents) {
            is NoteEvents.CreateNote -> {
                viewModelScope.launch {
                    notesUseCases.createNote(noteEvents.noteRequestModel)
                }
            }
            is NoteEvents.UpdateNote -> {
                viewModelScope.launch {
                    notesUseCases.updateNote(noteEvents.noteRequestModel)
                }
            }
            is NoteEvents.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCases.deleteNote(noteEvents.noteRequestModel)
                }
            }
            is NoteEvents.GetNoteById -> {
                viewModelScope.launch {
                    notesUseCases.getNoteById(noteEvents.id)
                }
            }
            NoteEvents.GetNotes -> {
                viewModelScope.launch {
                    notesUseCases.getNotes()
                }
            }
        }
    }
}