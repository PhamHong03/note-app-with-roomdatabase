package com.example.noteapp.domain.use_cases

data class NoteUseCases(
    val addNote: AddNote,
    val updateNote: UpdateNote,
    val deleteNote: DeleteNote,
    val getNote: GetNote,
    val getNotes: GetNotes
)
