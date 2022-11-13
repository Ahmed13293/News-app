package com.innovitics.newsfeedsapp.data.repository

import com.innovitics.asmiokotlin.data.repository.BaseRepository
import com.innovitics.newsfeedsapp.data.network.newsAPI
import javax.inject.Inject

class ExploreRepo @Inject constructor(
    private val newsApi: newsAPI
): BaseRepository() {
    suspend fun getEverythingNews(source: String) = safeApiCall {
        newsApi.everythingArticles(source)
    }

    suspend fun getTopHeadlinesArticles(source: String) = safeApiCall {
        newsApi.topHeadlinesArticles(source)
    }
}