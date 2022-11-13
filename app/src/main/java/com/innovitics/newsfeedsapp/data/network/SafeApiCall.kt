package com.innovitics.asmiokotlin.data.network

import com.innovitics.newsfeedsapp.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException

interface SafeApiCall {

    suspend fun <T> safeApiCall(
        id: Int? = null, position: Int? = null,
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke(), id, position)
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        val jObjError =
                            JSONObject(throwable.response()?.errorBody()?.string().toString())
                        Resource.Failure(
                            false,
                            throwable.code(),
                            ""
                        )
                    }
                    else -> {
                        Resource.Failure(
                            true, null,
                            ""
                        )
                    }
                }
            }
        }
    }
}