package com.bmruby.movie.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "upcoming_movie")
data class UpComingMovie(
    @PrimaryKey var id:Int,
    var original_language:String,
    var original_title:String,
    var overview:String,
    var poster_path:String,
    var backdrop_path:String,
    var title:String,
    var release_date:String,
    var adult:Boolean,
    var isFavourite:Boolean,
)
