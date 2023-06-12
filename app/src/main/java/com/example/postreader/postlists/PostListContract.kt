package com.example.postreader.postlists

import com.example.postreader.model.local.entity.Post


class PostListContract {
    data class State(
        val postLists: List<Post> = emptyList(),
        val isLoading: Boolean = true,
        val isError: String = ""
    )

    sealed class Effect {
        object PostLoaded : Effect()
        object NoPostFound : Effect()

        data class Error(
            val msg: String
        ) : Effect()
    }
}
