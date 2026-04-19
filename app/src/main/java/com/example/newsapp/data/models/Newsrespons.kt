package com.example.newsapp.data.models

data class Newsrespons (
    val articles: List<Article>
)
data class Article(
    val title: String,
    val description: String?,
    val urlToImage: String?
)