package com.example.mobilenews.data.repository

import com.example.mobilenews.data.model.Hit
import com.example.mobilenews.data.model.NewsModel
import com.example.mobilenews.data.model.NewsProvider
import com.example.mobilenews.data.network.NewsApiClient
import com.example.mobilenews.data.network.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsService,
    private val newsProvider: NewsProvider
){

    suspend fun getNews(): List<Hit> {
      val response = api.getLatestNews()
        newsProvider.newsList = response.toMutableList()
        return response
    }

}