package com.bmruby.movie.model.data

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response

fun <T> resultFlow(call: suspend () -> Response<T>): Flow<ApiState<T>> = flow {

    emit(ApiState.Loading)


    try {
        val response = call()

        if (response.isSuccessful) {
            response.body()?.let {
                emit(ApiState.Success(it))
            }
        } else {
            //todo fix
            response.errorBody()?.let { error ->
                val errorResponse = Gson().fromJson(
                    error.charStream(),
                    ErrorResponse::class.java)
                emit(ApiState.Failure(errorResponse.statusMessage,errorResponse.statusCode!!))
            }
        }

    } catch (e: Exception) {
        val Exception_Code = 8000
        emit(ApiState.Failure(e.message.toString(),Exception_Code))
    }

}
