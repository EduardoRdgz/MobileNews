package com.example.mobilenews.di.module

import android.app.Application
import android.content.Context
import com.example.mobilenews.data.network.NewsApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /*
     * @Provides annotation tells Dagger that this method provides a dependency.
     * @Singleton annotation ensures that only one instance of the Retrofit instance is created and reused throughout the application.
     */


    // Provides retrofit instance
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hn.algolia.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provides news api client
    @Singleton
    @Provides
    fun provideNewsApiClient(retrofit: Retrofit): NewsApiClient {
        return retrofit.create(NewsApiClient::class.java)
    }

}