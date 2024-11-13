package com.example.mobilenews.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsProvider @Inject constructor( ){
    var newsList: MutableList<Hit> = mutableListOf()
}