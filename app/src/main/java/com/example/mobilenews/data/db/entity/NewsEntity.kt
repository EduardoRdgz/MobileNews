package com.example.mobilenews.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mobilenews.data.model.Hit
import com.example.mobilenews.domain.model.New

@Entity(tableName = "news")
data class NewsEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val objectID: String? = "",
    @ColumnInfo val title: String? = "Not title available",
    @ColumnInfo val url: String? = "",
    @ColumnInfo val author: String? = "Not author available",
    @ColumnInfo val created_at: String? = "",
    @ColumnInfo val story_title: String? = "Not story title available",
    @ColumnInfo val story_url: String? = "Not story url available"
)

fun New.toDatabase() = NewsEntity(
    objectID = objectID,
    title = title,
    url = url,
    author = author,
    created_at = created_at,
    story_title = story_title,
    story_url = story_url
)
