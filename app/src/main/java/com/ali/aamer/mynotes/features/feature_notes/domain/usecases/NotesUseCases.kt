package com.ali.aamer.mynotes.features.feature_notes.domain.usecases

data class NotesUseCases(
    val getNotes: GetNotes,
    val createNote: CreateNote,
    val updateNote: UpdateNote,
    val deleteNote: DeleteNote,
    val getNoteById: GetNoteById
)