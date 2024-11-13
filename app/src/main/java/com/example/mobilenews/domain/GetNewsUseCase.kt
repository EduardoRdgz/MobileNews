package com.example.mobilenews.domain

import com.example.mobilenews.data.db.entity.toDatabase
import com.example.mobilenews.data.repository.NewsRepository
import com.example.mobilenews.domain.model.New
import javax.inject.Inject

class GetNewsUseCase  @Inject constructor(
    private val repository: NewsRepository
) {
    //This invoke function is called when the class is called
    suspend operator fun invoke(): List<New> {

        val news = repository.getNewsFromApi()

        // If the news list is not empty, clear the database and insert the new news
        // Else, get the news from the database
        return if(news.isNotEmpty()) {
            repository.clearNews()
            repository.insertNews(news.map { it.toDatabase() })
            news
        } else repository.getNewsFromDatabase()
    }

}