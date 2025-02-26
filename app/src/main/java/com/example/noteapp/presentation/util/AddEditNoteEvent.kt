package com.example.noteapp.presentation.util

sealed class AddEditNoteEvent {
    data class EnteredTitle(val title: String): AddEditNoteEvent()
    data class EnteredContent(val content: String): AddEditNoteEvent()
    object SaveNote: AddEditNoteEvent()
}