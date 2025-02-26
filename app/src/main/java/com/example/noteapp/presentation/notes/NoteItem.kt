package com.example.noteapp.presentation.notes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.noteapp.domain.model.Note
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    onDelete: ()->Unit
) {
    Box(
        modifier = modifier
    ){
        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(8.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                maxLines = 1
            )
            Text(
                text = note.content,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black,
                maxLines = 5
            )
        }
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            onClick = onDelete
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete note"
            )
        }
    }
}