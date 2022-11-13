package com.innovitics.newsfeedsapp.data.network


sealed class Resource<out T> {
    data class Success<out T>(val value: T, val id: Int? = null, val position: Int? = null) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean, val errorCode: Int?, val errorBody: String
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
//    object createObj:Resource<out T>
}