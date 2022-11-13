package com.innovitics.newsfeedsapp.data.responses

import com.innovitics.newsfeedsapp.data.models.ArticlesListModel

data class everythingResponse(
    val status: String,
    val articles: ArrayList<ArticlesListModel>
    )
