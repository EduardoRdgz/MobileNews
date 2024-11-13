package com.example.mobilenews.domain.model

import com.example.mobilenews.data.db.entity.NewsEntity
import com.example.mobilenews.data.model.Hit

data class New(
               val objectID: String?,
               val title: String?,
               val url: String?,
               val author: String?,
               val created_at: String?,
               val story_title: String?,
               val story_url: String?)


fun Hit.toNew() = New(objectID, title, url, author, created_at, story_title, story_url)

fun NewsEntity.toNew() = New(objectID, title, url, author, created_at, story_title, story_url)