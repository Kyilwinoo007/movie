package com.bmruby.movie.model.data
sealed class ApiState<out T> {
    data class Success<out R>(val data : R): ApiState<R>()
    data class Failure(val message:String,val code: Int): ApiState<Nothing>()
    object Loading: ApiState<Nothing>()
}
