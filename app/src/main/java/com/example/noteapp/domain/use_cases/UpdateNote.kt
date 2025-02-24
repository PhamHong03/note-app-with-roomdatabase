package com.example.noteapp.domain.use_cases

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.repository.NoteRepository

class UpdateNote (
    private val noteRepository: NoteRepository
){
    suspend operator fun invoke(note: Note) = noteRepository.updateNote(note)
}