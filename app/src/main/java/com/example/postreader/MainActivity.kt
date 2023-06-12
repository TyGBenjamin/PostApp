package com.example.postreader

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postreader.model.PostRepoImpl
import com.example.postreader.model.local.entity.Post
import com.example.postreader.model.remote.PostService
import com.example.postreader.postlists.PostListViewModel
import com.example.postreader.ui.theme.PostReaderTheme
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableList
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    //    private val apiService by lazy{ PostService}
//    private val repo:PostRepoImpl = PostRepoImpl(apiService)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostReaderTheme {
                val postViewModel = getViewModel<PostListViewModel>()
                LaunchedEffect(key1 = true) {
                    postViewModel.getPosts()
                }
                val postList = postViewModel.state.postLists

//                val postList = postViewModel.state.postLists
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(postList = postList)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    LaunchedEffect(key1 = true) {
        println("Viewmodel getting posts")
//                postViewModel.getPosts()
    }
    Log.e("WHATEVER", "Greeting: ")
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PostReaderTheme {
        Greeting("Android")
    }
}

@Composable
fun HomeScreen(postList: List<Post>) {
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
