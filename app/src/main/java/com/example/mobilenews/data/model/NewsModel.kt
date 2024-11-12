package com.example.mobilenews.data.model

data class NewsModel(val hits: List<Hit>)

data class Hit(
    val title: String,
    val url: String,
    val author: String,
    val created_at: String,
    val story_title: String,
    val story_url: String
)