package com.innovitics.newsfeedsapp.data.network

import com.innovitics.newsfeedsapp.data.responses.everythingResponse
import com.innovitics.newsfeedsapp.data.responses.topHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface newsAPI {

    @GET("everything")
    suspend fun everythingArticles(@Query("q") source: String): everythingResponse

    @GET("top-headlines")
    suspend fun topHeadlinesArticles(@Query("country") source: String): topHeadlinesResponse

}