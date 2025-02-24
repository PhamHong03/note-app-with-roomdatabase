package com.example.noteapp.domain.use_cases

import com.example.noteapp.domain.repository.NoteRepository

class GetNotes (
    private val noteRepository: NoteRepository
){
    operator fun invoke() = noteRepository.getNotes()
}