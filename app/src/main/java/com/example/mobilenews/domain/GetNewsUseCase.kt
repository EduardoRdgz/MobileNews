package com.example.mobilenews.domain

import com.example.mobilenews.data.db.entity.toDatabase
import com.example.mobilenews.data.repository.NetworkStatusRepository
import com.example.mobilenews.data.repository.NewsRepository
import com.example.mobilenews.domain.model.New
import javax.inject.Inject

class GetNewsUseCase  @Inject constructor(
    private val repository: NewsRepository,
    private val networkStatusRepository: NetworkStatusRepository
) {



    //This invoke function is called when the class is called
    suspend operator fun invoke(): List<New> {
        // If there is no internet connection, get the news from the database
        if (!networkStatusRepository.isNetworkAvailable()) {
            return repository.getNewsFromDatabase()
        } else {
            // If there is internet connection, get the news from the api
            repository.clearNews()
            val news = repository.getNewsFromApi()
            repository.insertNews(news.map { it.toDatabase() })
            return news
        }
    }

    suspend fun deleteNew(id: Int){
        repository.deleteNew(id)

    }

}