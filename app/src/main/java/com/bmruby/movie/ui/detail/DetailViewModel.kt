package com.bmruby.movie.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmruby.movie.model.data.Resource
import com.bmruby.movie.model.local.PopularMovie
import com.bmruby.movie.model.local.UpComingMovie
import com.bmruby.movie.repository.home.HomeRepository
import com.bmruby.movie.repository.home.state.PopularMovieState
import kotlinx.coroutines.launch

class DetailViewModel (val repository: HomeRepository): ViewModel() {
    var popularMovieState by mutableStateOf(PopularMovieDetailState())
    var upComingMovieState by mutableStateOf(UpComingMovieDetailState())

    fun getPopularMovieById(id:Int){
        viewModelScope.launch {
            repository.getPopularMovieById(id).collect{
                when(it){
                    is Resource.Success -> {
                        it.data?.let {
                                popularMovies ->
                            popularMovieState = popularMovieState.copy(movie = popularMovies)
                        }
                    }
                    is Resource.Loading -> {
                        it.data?.let {
                                popularMovies ->
                            popularMovieState = popularMovieState.copy(movie = popularMovies)
                        }
                    }
                    is Resource.Error -> {
                        it.error?.let {
                                error ->
                            error.message?.let {
                                    message ->
                                popularMovieState = popularMovieState.copy(movie = null, error = message)
                            }
                        }
                    }
                }
            }
        }
    }
    fun getUpComingMovieById(id:Int){
        viewModelScope.launch {
            repository.getUpComingMovieById(id).collect{
                when(it){
                    is Resource.Success -> {
                        it.data?.let {
                                movie ->
                            upComingMovieState = upComingMovieState.copy(movie = movie)
                        }
                    }
                    is Resource.Loading -> {
                        it.data?.let {
                                movie ->
                            upComingMovieState = upComingMovieState.copy(movie = movie)
                        }
                    }
                    is Resource.Error -> {
                        it.error?.let {
                                error ->
                            error.message?.let {
                                    message ->
                                upComingMovieState = upComingMovieState.copy(movie = null, error = message)
                            }
                        }
                    }
                }
            }
        }
    }

}

data class PopularMovieDetailState(
    val movie: PopularMovie? = null,
    val error:String = "",
)
data class UpComingMovieDetailState(
    val movie: UpComingMovie? = null,
    val error:String = "",
)
