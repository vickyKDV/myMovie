package com.vickykdv.mymovie.data.model.videos

import com.google.gson.annotations.SerializedName
import com.vickykdv.mymovie.data.model.videos.DataVideo

data class ResponseVideo(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val results: List<DataVideo>
)