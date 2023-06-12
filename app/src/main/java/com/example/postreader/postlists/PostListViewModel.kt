package com.example.postreader.postlists

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postreader.model.PostRepoImpl
import com.example.postreader.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class PostListViewModel(private val repo: PostRepoImpl) : ViewModel() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch {
            throwable.message
            effect.send(PostListContract.Effect.Error(throwable.message!!))
        }
    }
    var state by mutableStateOf(PostListContract.State())
        private set

    var effect = Channel<PostListContract.Effect>(Channel.UNLIMITED)
        private set


    init {
        getPosts()
    }

    fun getPosts() {
        Log.d("POSTVIEW", "getPosts: ")
        viewModelScope.launch(exceptionHandler) {
            val response = repo.getPosts()
            when (response) {
                is Resource.Error -> Resource.Error("Error")
                Resource.Idle -> Resource.Idle
                Resource.Loading -> Resource.Loading
                is Resource.Success -> {
                    Log.d("POSTVIEW", "Request Successful: ")

                    if (response.data.isEmpty()) {
                        effect.send(PostListContract.Effect.NoPostFound)
                    } else {
                        Log.d("POSTVIEW", "post gotten:${response.data} ")

                        effect.send(PostListContract.Effect.PostLoaded)
                        state = state.copy(
                            isLoading = true,
                            postLists = response.data
                        )
                    }
                }
            }
        }
    }
}
