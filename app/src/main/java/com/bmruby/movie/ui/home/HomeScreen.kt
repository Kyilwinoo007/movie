package com.bmruby.movie.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.bmruby.movie.R
import com.bmruby.movie.model.local.PopularMovie
import com.bmruby.movie.model.local.UpComingMovie
import com.bmruby.movie.model.uitls.AppConstants
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
            Text("UPCOMING MOVIES", fontFamily = FontFamily.Default, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        LazyHorizontalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            rows = GridCells.Fixed(count = 2),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
            contentPadding = PaddingValues(all = 4.dp)
        ) {
            items(homeViewModel.upComingMovieState.upComingMovie.size) { index ->
                var movie = homeViewModel.upComingMovieState.upComingMovie[index]
                MovieItem(movie = movie, onFavorite = {
                    id,isFavorite ->
                    homeViewModel.setUpComingMovieFavorite(id,isFavorite)
                })
            }
        }
    }
}

@Composable
fun  MovieItem(movie: UpComingMovie,onFavorite: (id: Int,isFavorite:Boolean) -> Unit){

    Column (modifier = Modifier.width(150.dp).height(250.dp).border(1.dp, color = Color.Gray,
        RectangleShape)){

                val posterPath = movie.poster_path
                val url = AppConstants.imageUlr + posterPath

                Card(
                    modifier = Modifier.wrapContentSize(),
                    shape = RoundedCornerShape(6.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ){
                    NetworkImage(url = url, contentDescription = "image",
                        modifier = Modifier.width(150.dp).height(200.dp))
                }
                Row( modifier = Modifier.fillMaxWidth()
                    .height(50.dp)
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = movie.title, modifier = Modifier.width(100.dp))
                    var isFavorite = movie.isFavourite != null && movie.isFavourite

                    Image(painter = if (isFavorite)
                        painterResource(id = R.drawable.favorite)
                        else
                        painterResource(id = R.drawable.un_favorite)   ,
                        contentDescription = "like", modifier = Modifier.size(24.dp).clickable {
                            onFavorite(movie.id,!isFavorite)
                        })


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
            Text("POPULAR MOVIES", fontFamily = FontFamily.Default, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        LazyHorizontalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            rows = GridCells.Fixed(count = 2),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
            contentPadding = PaddingValues(all = 4.dp)
        ) {
            items(homeViewModel.popularMovieState.popularMovies.size) { index ->
                var movie = homeViewModel.popularMovieState.popularMovies[index]
                PopularMovieItem(movie = movie) { id, isFavorite ->
                    homeViewModel.setPopularMovieFavorite(id, isFavorite)
                }
            }
        }
    }

}
@Composable
fun  PopularMovieItem(movie: PopularMovie,onFavorite: (id:Int,isFavorite:Boolean) -> Unit){

    Column (modifier = Modifier.width(150.dp).height(250.dp).border(1.dp, color = Color.Gray,
        RectangleShape)){

        val posterPath = movie.poster_path
        val url = AppConstants.imageUlr + posterPath

        Card(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(6.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ){
            NetworkImage(url = url, contentDescription = "image",
                modifier = Modifier.width(150.dp).height(200.dp))
        }
        Row( modifier = Modifier.fillMaxWidth()
            .height(50.dp)
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = movie.title, modifier = Modifier.width(100.dp))
            var isFavorite = movie.isFavourite != null && movie.isFavourite

            Image(painter = if (isFavorite)
                painterResource(id = R.drawable.favorite)
            else
                painterResource(id = R.drawable.un_favorite)   ,
                contentDescription = "like", modifier = Modifier.size(24.dp).clickable {
                    onFavorite(movie.id,!isFavorite)
                })


        }



    }
}
@Composable
fun NetworkImage(url: String, contentDescription: String?,
                 modifier: Modifier = Modifier
    .fillMaxWidth()
) {
    val painter: Painter = rememberImagePainter(url)
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}
