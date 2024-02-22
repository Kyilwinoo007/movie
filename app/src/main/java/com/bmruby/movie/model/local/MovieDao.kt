package com.bmruby.movie.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {


    @Query("SELECT * FROM popular_movie")
    fun getAllPopularMovie(): Flow<List<PopularMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPopularMovie(movies: List<PopularMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(products: PopularMovie)

    @Query("DELETE FROM popular_movie")
    suspend fun deleteAllPopularMovie()


}