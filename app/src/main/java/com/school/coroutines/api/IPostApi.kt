package com.school.coroutines.api

import com.school.coroutines.model.Item
import retrofit2.Response
import retrofit2.http.GET

interface IPostApi {
    @GET("/posts")
    suspend fun getPosts(): Response<List<Item>>
}