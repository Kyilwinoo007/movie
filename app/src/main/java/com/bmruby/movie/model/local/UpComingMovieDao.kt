package com.bmruby.movie.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UpComingMovieDao {
    @Query("SELECT * FROM upcoming_movie")
    fun getAllUpComingMovie(): Flow<List<UpComingMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUpComingMovie(movies: List<UpComingMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: UpComingMovie)

    @Query("DELETE FROM upcoming_movie")
    suspend fun deleteAllUpComingMovie()

    @Query("UPDATE upcoming_movie SET isFavourite = :isFavorite WHERE id =:id")
    fun update(isFavorite: Boolean?, id: Int)
    @Query("SELECT * FROM upcoming_movie WHERE id =:id")
    fun getMovieById(id: Int): Flow<UpComingMovie>
}