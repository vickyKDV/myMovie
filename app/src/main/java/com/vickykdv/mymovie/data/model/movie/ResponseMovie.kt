package com.vickykdv.mymovie.data.model.movie

import com.google.gson.annotations.SerializedName
import com.vickykdv.mymovie.data.model.movie.DataMovie

data class ResponseMovie (
    @SerializedName("results") val data : List<DataMovie>,
    @SerializedName("page") val page : Int,
    @SerializedName("total_pages") val total_pages : Int
)