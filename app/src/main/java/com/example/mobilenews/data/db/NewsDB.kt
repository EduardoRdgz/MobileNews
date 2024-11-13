package com.example.mobilenews.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobilenews.data.db.dao.NewsDao
import com.example.mobilenews.data.db.entity.NewsEntity

/*
 * Room database for storing news data.
 */
@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDB: RoomDatabase() {

    abstract fun newsDao(): NewsDao

}