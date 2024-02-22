package com.bmruby.movie.model.remote

import com.bmruby.movie.model.data.ApiResponse
import com.bmruby.movie.model.local.PopularMovie
import com.bmruby.movie.model.local.UpComingMovie
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiInterface {
    @GET("/3/movie/popular")
    suspend fun getPopularMovie(): ApiResponse<List<PopularMovie>>

    @GET("/3/movie/upcoming")
    suspend fun getUpComingMovie(): ApiResponse<List<UpComingMovie>>

    @GET("/3/movie/{id}")
    suspend fun getMovieById(
        @Path("id")id: Int) : PopularMovie

}