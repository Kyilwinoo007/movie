package com.bmruby.movie.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ErrorResponse(
    @SerializedName("status_code")
    var statusCode : Int,
    @SerializedName("status_message")
    var statusMessage: String,
    @SerializedName("success")
    var success:Boolean
): Serializable