package com.bmruby.movie.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bmruby.movie.ui.detail.PopularDetailScreen
import com.bmruby.movie.ui.detail.UpComingDetailScreen
import com.bmruby.movie.ui.home.HomeScreen
import com.bmruby.movie.ui.theme.MovieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val controller = rememberNavController()
                    NavHost(navController = controller,
                        route = MyGraph.Root,
                        startDestination = MyGraph.HOME){
                        composable(route = MyGraph.HOME){
                            HomeScreen(upComingDetail = {
                                    id ->
                                controller.navigate("upcomingDetail/$id")
                            }, popularDetail = {
                                id ->
                                controller.navigate("popularDetail/$id")
                            })
                        }
                        composable(route = "popularDetail/{id}",
                            arguments = listOf(
                                navArgument(name = "id"){
                                    type = NavType.IntType
                                }
                            )
                        ){
                            backStackEntry ->
                            PopularDetailScreen(backStackEntry.arguments?.getInt("id"))
                        }
                        composable(route = "upcomingDetail/{id}",
                            arguments = listOf(
                                navArgument(name = "id"){
                                    type = NavType.IntType
                                }
                            )
                        ){
                                backStackEntry ->
                            UpComingDetailScreen(backStackEntry.arguments?.getInt("id"))
                        }
                    }
                }
            }
        }
    }
}

object MyGraph{
    const val Root = "root_graph"
    const val HOME = "home"
    const val DETAIL = "detail"

}

