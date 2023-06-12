package com.example.postreader.model.remote.dto

import com.example.postreader.model.local.entity.Post

data class PostDTO(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
) {     fun postDTOToPost(): Post {
            return Post(
                body = this.body,
                id = this.id,
                title = this.title,
                userId = this.userId
            )
        }
}
