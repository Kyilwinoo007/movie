package com.bmruby.movie.repository.home.state

import com.bmruby.movie.model.local.PopularMovie

data class PopularMovieState(
    val popularMovies: List<PopularMovie> = emptyList(),
    val error:String = "",
)