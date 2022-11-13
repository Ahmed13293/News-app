package com.innovitics.newsfeedsapp.data.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.innovitics.newsfeedsapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ApiClient @Inject constructor(
    @ApplicationContext val context: Context,
) {

    var baseUrl = context.getString(R.string.base_url)


    fun <Api> buildApi(api: Class<Api>): Api {


        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val originalRequest = chain.request()
                        val httpUrl = originalRequest.url.newBuilder()
                            .addQueryParameter("apiKey", context.getString(R.string.apiKey))
                            .build()
                        val newRequest = originalRequest.newBuilder().url(httpUrl).build()
                        chain.proceed(newRequest.newBuilder().also {
                            it.addHeader("Accept", "application/json")


                        }.build())
                    }.also { client ->
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)

                    }

                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(api)

    }

}

