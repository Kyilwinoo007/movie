package com.bmruby.movie.model.remote

import com.bmruby.movie.model.uitls.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient {

    companion object {
        private var retrofit: Retrofit? = null
        fun getClient(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.HEADERS

            val okHttpClient = OkHttpClient
                .Builder()
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(AuthInterceptor())
                .build()
            retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit!!
        }

        fun apiService(): ApiInterface {
            return getClient().create(ApiInterface::class.java)
        }

    }

}