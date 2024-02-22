package com.bmruby.movie.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [PopularMovie::class,UpComingMovie::class], version = 1, exportSchema = false)
abstract class MovieDb : RoomDatabase() {

    abstract  fun  getDao() : MovieDao
    abstract  fun  getUpComingDao() : UpComingMovieDao
}
/**
 * Migration(1, 2) means from database version 1 to 2*/
val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // We can perform actions like
        /*database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
                "PRIMARY KEY(`id`))")*/
    }
}