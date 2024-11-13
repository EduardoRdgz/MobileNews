package com.example.mobilenews.data.repository

import com.example.mobilenews.data.db.dao.NewsDao
import com.example.mobilenews.data.db.entity.NewsEntity
import com.example.mobilenews.data.model.Hit
import com.example.mobilenews.data.model.NewsProvider
import com.example.mobilenews.data.network.NewsService
import com.example.mobilenews.domain.model.New
import com.example.mobilenews.domain.model.toNew
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsService,
    private val newsProvider: NewsProvider,
    private val newsDao: NewsDao
){

    suspend fun getNewsFromApi(): List<New> {
      val response : List<Hit> = api.getLatestNews()
        return response.map { it.toNew()}
    }

    suspend fun getNewsFromDatabase(): List<New> {
        val response = newsDao.getNews()
        return response.map { it.toNew() }
    }

    suspend fun insertNews(news: List<NewsEntity>){
        newsDao.insertNews(news)
    }

    suspend fun clearNews(){
        newsDao.clearNews()
    }

}