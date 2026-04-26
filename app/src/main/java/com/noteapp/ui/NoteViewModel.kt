package com.noteapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noteapp.NoteEntity
import com.noteapp.NoteEntityQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel(private val queries: NoteEntityQueries) : ViewModel() {

    private val _notes = MutableStateFlow<List<NoteEntity>>(emptyList())
    val notes: StateFlow<List<NoteEntity>> = _notes

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadNotes()
    }

    fun loadNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true

            val result = queries.getAllNotes().executeAsList()

            _notes.value = result
            _isLoading.value = false
        }
    }

    fun addNote(title: String, content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            queries.insertNote(
                id = null,
                title = title,
                content = content,
                createdAt = System.currentTimeMillis(),
                isFavorite = 0
            )
            loadNotes()
        }
    }
    fun getNoteById(id: Int, onResult: (NoteEntity?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            // Mencari note dari list yang sudah ada di memory
            val note = _notes.value.find { it.id == id.toLong() }
            onResult(note)
        }
    }
    // Fungsi untuk mengambil 1 catatan saja berdasarkan ID
    fun searchNotes(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (query.isEmpty()) {
                loadNotes() // Kalau kosong, tampilin semua
            } else {
                val results = queries.searchNotes(query).executeAsList()
                _notes.value = results
            }
        }
    }

    // Fungsi Update (Poin 2)
    fun updateNote(id: Int, title: String, content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            queries.updateNote(title, content, id.toLong())
            loadNotes() // Refresh list utama
        }
    }

    // Fungsi Delete (Poin 2)
    fun deleteNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            queries.deleteNote(id.toLong())
            loadNotes() // Refresh list utama
        }
    }
}