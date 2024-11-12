package com.example.mobilenews.data.network

import com.example.mobilenews.data.model.NewsModel
import retrofit2.http.GET

interface NewsApiClient {
    @GET("/.json")
    suspend fun  getLatestNews(): List<NewsModel>
}