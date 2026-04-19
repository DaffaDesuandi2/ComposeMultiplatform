package com.example.newsapp.data.repository

import com.example.newsapp.data.api.NewsApiService

class NewsRepository(private val apiService: NewsApiService) {
    suspend fun getArticles() = apiService.getNews()
}