package com.example.noteapp.presentation.notes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    navController: NavController,
    notesViewModel: NotesViewModel = hiltViewModel()
){
    val notes = notesViewModel.noteState.value
    val snackbarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(route = "add_note_screen")
                },
                content = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn (
            modifier = Modifier.fillMaxHeight()
        ){
            items(notes) {note ->
                NoteItem(
                    note = note,
                    modifier = Modifier.fillMaxWidth(),
                    onDelete = {
                        notesViewModel.onEvent(NoteEvent.DeleteNote(note))
                        scope.launch {
                            val result = snackbarHostState.showSnackbar(
                                "Note deleted",
                                "Undo"
                            )
                            if(result==SnackbarResult.ActionPerformed){
                                notesViewModel.onEvent(NoteEvent.RestoreNote)
                            }
                        }
                    }
                )
            }
        }
    }
}