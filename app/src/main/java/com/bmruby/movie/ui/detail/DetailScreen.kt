package com.bmruby.movie.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bmruby.movie.R
import com.bmruby.movie.model.uitls.AppConstants
import com.bmruby.movie.ui.home.HomeViewModel
import com.bmruby.movie.ui.home.NetworkImage
import com.bmruby.movie.ui.home.PopularMovieWidget
import com.bmruby.movie.ui.home.UpComingMovieWidget
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularDetailScreen(id: Int?) {
    val detailViewModel: DetailViewModel = koinViewModel()
    val homeViewModel: HomeViewModel = koinViewModel()
    id?.let {
        detailViewModel.getPopularMovieById(id)
    }
    var movie = detailViewModel.popularMovieState.movie
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black),) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally,){
            Card(
                modifier = Modifier.wrapContentSize(),
                shape = RoundedCornerShape(6.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ){
                val posterPath = movie?.backdrop_path
                val url = AppConstants.imageUlr + posterPath
                NetworkImage(url = url, contentDescription = "image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp))

                Row( modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    movie?.title?.let { title -> Text(text = title, modifier = Modifier.width(100.dp)) }
                    val isFavorite = movie?.isFavourite != null && movie.isFavourite

                    Image(painter = if (isFavorite)
                        painterResource(id = R.drawable.favorite)
                    else
                        painterResource(id = R.drawable.un_favorite)   ,
                        contentDescription = "like", modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                movie?.id?.let { id -> homeViewModel.setPopularMovieFavorite(id,!isFavorite) }
                            })


                }
                movie?.overview?.let { overview -> Text(text = overview, modifier = Modifier.fillMaxSize()) }


            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpComingDetailScreen(id:Int?){
    val detailViewModel: DetailViewModel = koinViewModel()
    val homeViewModel: HomeViewModel = koinViewModel()
    id?.let {
        detailViewModel.getUpComingMovieById(id)
    }
    var movie = detailViewModel.upComingMovieState.movie

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black),) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally,){

            Card(
                modifier = Modifier.wrapContentSize(),
                shape = RoundedCornerShape(6.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ){
                val posterPath = movie?.backdrop_path
                val url = AppConstants.imageUlr + posterPath
                NetworkImage(url = url, contentDescription = "image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp))

                Row( modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    movie?.title?.let { title -> Text(text = title, modifier = Modifier.width(100.dp)) }
                    val isFavorite = movie?.isFavourite != null && movie.isFavourite

                    Image(painter = if (isFavorite)
                        painterResource(id = R.drawable.favorite)
                    else
                        painterResource(id = R.drawable.un_favorite)   ,
                        contentDescription = "like", modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                movie?.id?.let { id -> homeViewModel.setUpComingMovieFavorite(id,!isFavorite) }
                            })


                }
                movie?.overview?.let { overview -> Text(text = overview, modifier = Modifier.fillMaxSize()) }
            }
        }
    }
}