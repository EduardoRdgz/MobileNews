package com.example.mobilenews.di.module

import android.content.Context
import androidx.room.Room
import com.example.mobilenews.data.db.NewsDB
import com.example.mobilenews.data.db.dao.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    const val DB_NAME = "news_database"

    // Provide the NewsDB instance
    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): NewsDB {
        return Room.databaseBuilder(
            context,
            NewsDB::class.java,
            DB_NAME
        ).build()
    }

    // Provide the NewsDao instance
    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDB) = db.newsDao()

}