package com.example.postreader.util

sealed class Resource<out T>{
    data class Success<T>(val data:T): Resource<T>()
    object Loading: Resource<Nothing>()
    object Idle: Resource<Nothing>()
    data class Error(val error:String): Resource<Nothing>()
}
