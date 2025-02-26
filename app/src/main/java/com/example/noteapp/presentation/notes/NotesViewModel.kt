package com.example.noteapp.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.use_cases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _noteState = mutableStateOf(emptyList<Note>())
    val noteState:State<List<Note>> = _noteState

    private var job:Job? = null
    private var deleteNote:Note? = null

    fun onEvent(event: NoteEvent){
        if(event is NoteEvent.DeleteNote){
            viewModelScope.launch {
                noteUseCases.deleteNote(event.note)
            }
            deleteNote = event.note
        }else{
            viewModelScope.launch {
                noteUseCases.addNote(deleteNote!!)
            }
        }
    }

    private fun getNotes(){
        job?.cancel()

        job = viewModelScope.launch {
            noteUseCases.getNotes().onEach {
                _noteState.value = it
            }
        }
    }
}