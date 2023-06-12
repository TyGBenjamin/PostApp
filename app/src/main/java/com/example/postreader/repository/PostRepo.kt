package com.example.postreader.repository

import com.example.postreader.model.local.entity.Post
import com.example.postreader.model.remote.dto.PostDTO
import com.example.postreader.util.Resource

interface PostRepo {
    suspend fun getPosts() : Resource<List<Post>>

}
