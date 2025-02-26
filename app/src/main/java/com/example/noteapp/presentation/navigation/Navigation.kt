package com.example.noteapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noteapp.presentation.add_note.AddNoteScreen
import com.example.noteapp.presentation.notes.NoteScreen

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main_screen") {
        composable(route = "main_screen") {
            NoteScreen(navController = navController)
        }
        composable(route = "add_note_screen") {
            AddNoteScreen(navController = navController)
        }

    }
}