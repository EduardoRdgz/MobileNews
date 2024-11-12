package com.example.mobilenews.data.network

import com.example.mobilenews.data.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

interface NewsApiClient {
    @GET("/api/v1/search_by_date")
    suspend fun  getLatestNews(): Response<NewsModel>
}