package com.vickykdv.mymovie.state

import com.vickykdv.mymovie.data.model.detailmovie.ResponseDetailMovie



sealed class DetailMovieState {
    object Loading : DetailMovieState()
    data class Result(val data : ResponseDetailMovie) : DetailMovieState()
    data class Error(val error : Throwable) : DetailMovieState()
}