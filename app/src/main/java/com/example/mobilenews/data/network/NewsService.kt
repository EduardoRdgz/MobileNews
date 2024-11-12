package com.example.mobilenews.data.network

import com.example.mobilenews.data.model.Hit
import com.example.mobilenews.data.model.NewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsService @Inject constructor(
    private val api: NewsApiClient
){

    suspend fun getLatestNews(): List<Hit> {
        return withContext(Dispatchers.IO) {
            val response = api.getLatestNews()
            response.body()?.hits ?: emptyList<Hit>()
        }
    }
}