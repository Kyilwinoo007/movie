package com.bmruby.movie.repository.home

import android.util.Log
import androidx.room.withTransaction
import com.bmruby.movie.model.data.ApiState
import com.bmruby.movie.model.data.Resource
import com.bmruby.movie.model.data.networkBoundResource
import com.bmruby.movie.model.local.MovieDao
import com.bmruby.movie.model.local.MovieDb
import com.bmruby.movie.model.local.PopularMovie
import com.bmruby.movie.model.local.UpComingMovie
import com.bmruby.movie.model.local.UpComingMovieDao
import com.bmruby.movie.model.remote.ApiClient
import com.bmruby.movie.model.remote.ApiInterface
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.ApiStatus


class HomeRepositoryImpl(private val apiClient: ApiInterface,
                         val db : MovieDb,
                         private val movieDao:MovieDao,
                        private val upComingMovieDao: UpComingMovieDao): HomeRepository {

    //todo here
    //if api is get save to db
    //else return from db
    override fun getPopularMovie(): Flow<Resource<List<PopularMovie>>> = networkBoundResource(

        query = {
            movieDao.getAllPopularMovie()
        },
        fetch = {
            delay(1000)
            apiClient.getPopularMovie().results
        },
        saveFetchResult = { data ->
            data?.let {
                db.withTransaction {
                    movieDao.deleteAllPopularMovie()
                    movieDao.insertAllPopularMovie(it)
                }
            }

        }
    )


    override fun getUpComingMovie(): Flow<Resource<List<UpComingMovie>>> = networkBoundResource(
        query = {
            upComingMovieDao.getAllUpComingMovie()
        },
        fetch = {
            delay(1000)
            apiClient.getUpComingMovie().results
        },
        saveFetchResult = { data ->
            data?.let {
                db.withTransaction {
                    upComingMovieDao.deleteAllUpComingMovie()
                    upComingMovieDao.insertAllUpComingMovie(it)
                }
            }

        }
    )
}