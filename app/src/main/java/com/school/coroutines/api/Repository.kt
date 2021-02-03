package com.school.coroutines.api

import com.school.coroutines.model.Item
import com.school.coroutines.view.MainActivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object Repository {

    suspend fun getPosts() =
        NetworkSource.getPosts()

    object NetworkSource {
        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        private val postApi = retrofit.create(IPostApi::class.java)

        suspend fun getPosts() = postApi.getPosts()
    }
}
