package com.bmruby.movie.model.remote

import com.bmruby.movie.model.remote.ApiClient
import com.bmruby.movie.model.uitls.AppConstants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = AppConstants.accessToken
        val language = AppConstants.language
        val request = chain.request()

        var newRequest = request
            token.also {
                 newRequest = request
                    .newBuilder()
                    .header("Authorization", "Bearer $token")
                     .header("Accept-Language", language)
                     .build()
            }
        return  chain.proceed(newRequest)

    }
}