package com.example.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.models.Article
import com.example.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    val articles = MutableLiveData<List<Article>?>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    fun fetchNews() {
        viewModelScope.launch {
            isLoading.value = true
            isError.value = false
            try {
                val response = repository.getArticles()
                articles.value = response.articles
                isLoading.value = false
            } catch (e: Exception) {
                isError.value = true
                isLoading.value = false
            }
        }
    }
}