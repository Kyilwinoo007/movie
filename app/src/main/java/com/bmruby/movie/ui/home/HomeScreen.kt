package com.bmruby.movie.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(){
    val homeViewModel: HomeViewModel =  koinViewModel()
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black),) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally,){
            PopularMovieWidget(homeViewModel)
            UpComingMovie(homeViewModel)
        }
    }
}

@Composable
fun UpComingMovie(homeViewModel: HomeViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("UpComing Movies", fontFamily = FontFamily.Default, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        LazyHorizontalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            rows = GridCells.Fixed(count = 1),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
            contentPadding = PaddingValues(all = 4.dp)
        ) {
            items(homeViewModel.upComingMovieState.upComingMovie.size) { index ->
                var movie = homeViewModel.upComingMovieState.upComingMovie[index]
                Text(text = movie.title)
//                GameItem(game = game.games[index], gameLaunch = onClick)
            }
        }
    }
}

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun PopularMovieWidget(homeViewModel: HomeViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Popular Movies", fontFamily = FontFamily.Default, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        LazyHorizontalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            rows = GridCells.Fixed(count = 1),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
            contentPadding = PaddingValues(all = 4.dp)
        ) {
            items(homeViewModel.popularMovieState.popularMovies.size) { index ->
                var movie = homeViewModel.popularMovieState.popularMovies[index]
                Text(text = movie.title)
//                GameItem(game = game.games[index], gameLaunch = onClick)
            }
        }
    }

}
