package com.bmruby.movie.repository.home

import com.bmruby.movie.model.data.Resource
import com.bmruby.movie.model.local.PopularMovie
import com.bmruby.movie.model.local.UpComingMovie
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getPopularMovie(): Flow<Resource<List<PopularMovie>>>
    fun getUpComingMovie(): Flow<Resource<List<UpComingMovie>>>
    suspend fun setUpComingMovieFavorite(id: Int, isFavorite: Boolean):Resource<Unit>
    suspend fun setPopularMovieFavorite(id: Int, favorite: Boolean):Resource<Unit>
}