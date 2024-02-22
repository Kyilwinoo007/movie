package com.bmruby.movie.repository.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import androidx.room.withTransaction
import com.bmruby.movie.MyApplication
import com.bmruby.movie.model.data.Resource
import com.bmruby.movie.model.data.networkBoundResource
import com.bmruby.movie.model.local.MovieDao
import com.bmruby.movie.model.local.MovieDb
import com.bmruby.movie.model.local.PopularMovie
import com.bmruby.movie.model.local.UpComingMovie
import com.bmruby.movie.model.local.UpComingMovieDao
import com.bmruby.movie.model.remote.ApiInterface
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import java.io.IOException


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
        shouldFetch = {
                      isInternetAvailable()
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
        shouldFetch = {
            isInternetAvailable()
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
    private fun isInternetAvailable(): Boolean {
            var connMgr = MyApplication.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var networkInfo = connMgr.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());

    }

    override suspend fun setUpComingMovieFavorite(id: Int, isFavorite: Boolean):Resource<Unit> {
            return try {
                db.withTransaction {
                    upComingMovieDao.update(isFavorite = isFavorite,id)
                }
                Resource.Success(Unit)
            } catch (e: Exception) {
                Resource.Error(e)
            }

    }

    override suspend fun setPopularMovieFavorite(id: Int, favorite: Boolean): Resource<Unit> {
        return try {
            db.withTransaction {
                movieDao.update(isFavorite = favorite,id)
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}