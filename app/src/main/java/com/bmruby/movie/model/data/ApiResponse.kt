package com.bmruby.movie.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ApiResponse<T> : Serializable {

    @SerializedName("page")
    var page :Int? = null

    @SerializedName("total_pages")
    var totalPages :Int? = null

    @SerializedName("total_results")
    var totalResult:Int? = null

    @SerializedName("results")
    var results: T? = null


}