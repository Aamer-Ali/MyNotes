package com.ali.aamer.mynotes.features.feature_notes.display.events

import com.ali.aamer.notesapp.models.notes.NoteRequestModel

sealed class NoteEvents {
    data class CreateNote(val noteRequestModel: NoteRequestModel) : NoteEvents()
    data class UpdateNote(val noteRequestModel: NoteRequestModel) : NoteEvents()
    data class GetNoteById(val id: String) : NoteEvents()
    data class DeleteNote(val noteRequestModel: NoteRequestModel) : NoteEvents()
    object GetNotes : NoteEvents()
}