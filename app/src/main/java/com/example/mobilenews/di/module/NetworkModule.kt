package com.example.mobilenews.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
            .baseUrl("http://hn.algolia.com/api/v1/search_by_date?")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}