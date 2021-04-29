package com.vickykdv.mymovie.state

import com.vickykdv.mymovie.data.model.movie.ResponseMovie



sealed class MovieState {
    object Loading : MovieState()
    data class Result(val data : ResponseMovie) : MovieState()
    data class Error(val error : Throwable) : MovieState()
}