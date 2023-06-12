package com.example.postreader.model.remote

import com.example.postreader.model.remote.dto.InitialResponse
import com.example.postreader.model.remote.dto.PostDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface PostService {
    @GET(POST_ENDPOINT)
    suspend fun getPosts(): Response<List<PostDTO>>
    companion object{
        const val BASE_URL ="https://jsonplaceholder.typicode.com/"
        const val POST_ENDPOINT = "posts"
    }

    fun getApiService(): PostService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create()
}
