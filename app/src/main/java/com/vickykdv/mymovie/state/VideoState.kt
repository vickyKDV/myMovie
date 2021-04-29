package com.vickykdv.mymovie.state

import com.vickykdv.mymovie.data.model.videos.ResponseVideo



sealed class VideoState {
    object Loading : VideoState()
    data class Result(val data : ResponseVideo) : VideoState()
    data class Error(val error : Throwable) : VideoState()
}