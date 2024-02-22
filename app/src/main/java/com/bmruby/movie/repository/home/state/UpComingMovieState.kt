package com.bmruby.movie.repository.home.state

import com.bmruby.movie.model.local.UpComingMovie

data class UpComingMovieState(
    val upComingMovie: List<UpComingMovie> = emptyList(),
    val error:String = "",
)