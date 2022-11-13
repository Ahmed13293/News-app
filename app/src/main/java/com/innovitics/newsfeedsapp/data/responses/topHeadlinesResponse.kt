package com.innovitics.newsfeedsapp.data.responses

import com.innovitics.newsfeedsapp.data.models.ArticlesListModel

data class topHeadlinesResponse(
    val status: String,
    val articles: ArrayList<ArticlesListModel>
)
