package com.bmruby.movie.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmruby.movie.repository.home.state.PopularMovieState
import com.bmruby.movie.model.data.Resource
import com.bmruby.movie.repository.home.HomeRepository
import com.bmruby.movie.repository.home.state.UpComingMovieState
import kotlinx.coroutines.launch


class HomeViewModel(val repository: HomeRepository) : ViewModel() {
    var popularMovieState by mutableStateOf(PopularMovieState())
    var upComingMovieState by mutableStateOf(UpComingMovieState())

    init {
        getPopularMovie()
        getUpComingMovie()
    }
   private fun getPopularMovie(){
        viewModelScope.launch {
            repository.getPopularMovie().collect{
                when(it){
                    is Resource.Success -> {
                        it.data?.let {
                                popularMovies ->
                            popularMovieState = popularMovieState.copy(popularMovies = popularMovies)
                        }
                    }
                    is Resource.Loading -> {
                        it.data?.let {
                            popularMovies ->
                            popularMovieState = popularMovieState.copy(popularMovies = popularMovies)
                        }
                    }
                    is Resource.Error -> {
                        it.error?.let {
                            error ->
                            error.message?.let {
                                message ->
                                popularMovieState = popularMovieState.copy(popularMovies = emptyList(), error = message)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getUpComingMovie(){
        viewModelScope.launch {
            repository.getUpComingMovie().collect{
                when(it){
                    is Resource.Success -> {
                        it.data?.let {
                                movies ->
                            upComingMovieState =
                                upComingMovieState.copy(upComingMovie = movies)
                        }
                    }
                    is Resource.Loading -> {
                        it.data?.let {
                                movies ->
                            upComingMovieState =
                                upComingMovieState.copy(upComingMovie = movies)
                        }
                    }
                    is Resource.Error -> {
                        it.error?.let {
                                error ->
                            error.message?.let {
                                    message ->
                                upComingMovieState  = upComingMovieState.copy(upComingMovie = emptyList(), error = message)
                            }
                        }
                    }
                }
            }
        }
    }

    fun setUpComingMovieFavorite(id: Int,isFavorite:Boolean) {
        viewModelScope.launch {
            repository.setUpComingMovieFavorite(id,isFavorite)

        }
    }

    fun setPopularMovieFavorite(id: Int, favorite: Boolean) {
        viewModelScope.launch {
            repository.setPopularMovieFavorite(id,favorite)

        }
    }
}