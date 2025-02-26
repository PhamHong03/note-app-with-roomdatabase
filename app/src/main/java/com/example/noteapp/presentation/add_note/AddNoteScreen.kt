package com.example.noteapp.presentation.add_note

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteapp.presentation.util.AddEditNoteEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddNoteScreen(
    navController: NavController,
    addNoteViewModel: AddNoteViewModel = hiltViewModel()
) {
    val title = addNoteViewModel.title.value
    val content = addNoteViewModel.content.value

    val scaffoldState = remember { SnackbarHostState() }
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if(title.isBlank()){
                        Toast.makeText(context, "Title is empty", Toast.LENGTH_SHORT).show()
                        return@FloatingActionButton
                    }
                    if(content.isBlank()){
                        Toast.makeText(context, "Content is empty", Toast.LENGTH_SHORT).show()
                        return@FloatingActionButton
                    }
                    addNoteViewModel.onEvent(AddEditNoteEvent.SaveNote)
                    navController.navigateUp()
                },
                content = {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Save note")
                }
            )
        },
        snackbarHost = { SnackbarHost(scaffoldState) }
    ) {
        Column (
            modifier = Modifier
                .padding(8.dp)
        ){
            OutlinedTextField(
                value = title,
                onValueChange = {
                    addNoteViewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                },
                label = {
                    Text(
                        text = "Title"
                    )
                },
                textStyle = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = content,
                onValueChange = {
                    addNoteViewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                },
                label = {
                    Text(
                        text = "Content"
                    )
                },
                textStyle = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxHeight().padding(32.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

        }
    }

}