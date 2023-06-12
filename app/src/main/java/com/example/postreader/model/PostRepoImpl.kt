package com.example.postreader.model

import android.util.Log
import com.example.postreader.model.local.entity.Post
import com.example.postreader.model.remote.PostService
import com.example.postreader.repository.PostRepo
import com.example.postreader.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepoImpl(private val apiService: PostService) : PostRepo {
    override suspend fun getPosts(): Resource<List<Post>> = withContext(Dispatchers.IO) {
        Log.d("REPO", "getPosts: function triggered")
        val response = apiService.getPosts()
        return@withContext try {
            if (response.isSuccessful && response.body() != null) {
                Log.d("REPO", "getPosts: posts successfully triggered")
                Resource.Success(response.body()!!.map { it.postDTOToPost() })
            } else {
                Resource.Error(response.message())
            }
        } catch (e: IllegalArgumentException) {
            Resource.Error(e.message.toString())
        }
    }
}

