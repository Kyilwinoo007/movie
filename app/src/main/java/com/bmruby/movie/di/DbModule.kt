package com.bmruby.movie.di

import android.content.Context
import android.graphics.Movie
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bmruby.movie.model.local.MIGRATION_1_2
import com.bmruby.movie.model.local.MovieDao
import com.bmruby.movie.model.local.MovieDb
import com.bmruby.movie.model.local.UpComingMovieDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "Movie_DB"
val dbModule = module {
    single { provideRoomDatabase(androidContext()) }
    single { provideMovieDao(get()) }
    single { provideUpComingDao(get()) }
}


fun provideRoomDatabase(context: Context): MovieDb {
    var database: MovieDb? = null
    database = Room.databaseBuilder(context, MovieDb::class.java, DATABASE_NAME)
        .addCallback(object : RoomDatabase.Callback() {
        })
        .addMigrations(MIGRATION_1_2)
        .fallbackToDestructiveMigration()
        .build()

    return database
}

private fun provideMovieDao(appDatabase: MovieDb): MovieDao {
    return appDatabase.getDao()
}

private fun provideUpComingDao(appDatabase: MovieDb): UpComingMovieDao {
    return appDatabase.getUpComingDao()
}