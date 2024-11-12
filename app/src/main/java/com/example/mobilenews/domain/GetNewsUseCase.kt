package com.example.mobilenews.domain

import com.example.mobilenews.data.model.Hit
import com.example.mobilenews.data.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase  @Inject constructor(
    private val repository: NewsRepository
) {
    //This invoke function is called when the class is called
    suspend operator fun invoke(): List<Hit>? = repository.getNews()
}