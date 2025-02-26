package com.example.noteapp.presentation.notes

import com.example.noteapp.domain.model.Note


sealed class NoteEvent {
    data class DeleteNote(val note: Note): NoteEvent()

    object RestoreNote: NoteEvent()

}