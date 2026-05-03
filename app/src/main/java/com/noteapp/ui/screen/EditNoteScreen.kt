package com.noteapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.noteapp.ui.NoteViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.noteapp.ui.AiViewModel
import org.koin.compose.koinInject

@Composable
fun NoteAiSection(viewModel: AiViewModel = koinInject(), noteContent: String) {
    val summary by viewModel.uiState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = { viewModel.summarizeNote(noteContent) },
            enabled = !isLoading
        ) {
            if (isLoading) CircularProgressIndicator(modifier = Modifier.size(20.dp),
                strokeWidth = 2.dp,
                color = MaterialTheme.colorScheme.primary)
            else Text("Ringkas pakai AI ✨")
        }

        summary?.let {
            Card(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                Text(text = it, modifier = Modifier.padding(8.dp))
            }
        }
    }
}
@Composable
fun EditNoteSceen(noteId: Int, navController: NavHostController, viewModel: NoteViewModel) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    // Ambil data lama dari DB pas layar dibuka
    LaunchedEffect(noteId) {
        viewModel.getNoteById(noteId) { note ->
            note?.let {
                title = it.title
                content = it.content
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Judul") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Isi Catatan") },
            modifier = Modifier.fillMaxWidth().weight(1f)
        )
        NoteAiSection(noteContent = content)
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // Tombol Hapus (Poin 2)
            Button(
                onClick = {
                    viewModel.deleteNote(noteId)
                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Hapus", color = Color.White)
            }

            // Tombol Update (Poin 2)
            Button(
                onClick = {
                    viewModel.updateNote(noteId, title, content)
                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Update")
            }
        }
    }
}