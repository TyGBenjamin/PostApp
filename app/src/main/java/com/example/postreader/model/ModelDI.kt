package com.example.postreader.model

import com.example.postreader.model.remote.PostService
import com.example.postreader.postlists.PostListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create



    val appModule = module {
        single {
            Retrofit.Builder()
                .baseUrl(PostService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        single {
            PostService
        }

        single {
            get<Retrofit>().create<PostService>()
        }

        single {
            PostRepoImpl(get())
        }

        viewModel {
            PostListViewModel(get())
        }
    }

