package com.innovitics.newsfeedsapp.data.models

data class ArticlesListModel(
    val source: SourceObject,
    val author: String,
    val title: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)
