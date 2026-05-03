package com.noteapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AiViewModel(private val generativeModel: GenerativeModel) : ViewModel() {

    // State untuk hasil teks dari AI
    private val _uiState = MutableStateFlow<String?>(null)
    val uiState = _uiState.asStateFlow()

    // State untuk loading (biar bisa nampilin indikator muter-muter)
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun summarizeNote(promptUser: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _uiState.value = null

            try {
                val systemPrompt = "Kamu adalah asisten catatan yang cerdas. " +
                        "Tolong ringkas atau jawab pertanyaan ini berdasarkan catatan: "

                val response = generativeModel.generateContent(systemPrompt + promptUser)

                _uiState.value = response.text
            } catch (e: Exception) {
                _uiState.value = "Aduh, AI-nya lagi pusing: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearResult() {
        _uiState.value = null
    }
}