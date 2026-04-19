package com.example.newsapp.data.api

import com.example.newsapp.data.models.Newsrespons
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "153c55704d294ed88656843fa4d01604"
    ): Newsrespons
}



