package com.innovitics.newsfeedsapp.di

import com.innovitics.newsfeedsapp.data.network.ApiClient
import com.innovitics.newsfeedsapp.data.network.newsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNewsApi(remoteDataSource: ApiClient): newsAPI {
        return remoteDataSource.buildApi(newsAPI::class.java)
    }
}
