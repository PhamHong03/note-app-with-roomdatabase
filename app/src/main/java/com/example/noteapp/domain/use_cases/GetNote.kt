package com.example.noteapp.domain.use_cases

import com.example.noteapp.domain.repository.NoteRepository

class GetNote (
    private val noteRepository: NoteRepository
){
    suspend operator fun invoke(id: Int) = noteRepository.getNote(id)
}