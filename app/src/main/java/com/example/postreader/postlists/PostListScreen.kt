package com.example.postreader.postlists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postreader.model.local.entity.Post



    @Composable
    fun PostScreen() {
        val postViewModel: PostListViewModel = viewModel()
        postViewModel.getPosts()
        val postList = postViewModel.state.postLists
        LazyColumn(state = rememberLazyListState(), modifier = Modifier.padding(5.dp)) {
            items(items = postList) {
                PostCard(post = it)
            }
        }
    }

    @Composable
    fun PostCard(post: Post) {
        Card() {
            Row() {
                Text(text = post.title)
            }
            Column() {
                Text(text = post.body)
            }
        }
    }
