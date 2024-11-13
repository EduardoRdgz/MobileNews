package com.example.mobilenews.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobilenews.data.db.entity.NewsEntity
import com.example.mobilenews.data.model.Hit

/*
 * Data Access Object (DAO) for accessing the news database.
 */
@Dao
interface NewsDao {

    // Get all news from the database
    @Query("SELECT * FROM news")
    suspend fun getNews(): List<NewsEntity>

    // Insert news into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<NewsEntity>)

    // Clear all news from the database
    @Query("DELETE FROM news")
    suspend fun clearNews()

    @Query("DELETE FROM news WHERE objectID = :id")
    suspend fun deleteNew(id: Int)



}