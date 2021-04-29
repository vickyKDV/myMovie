package com.vickykdv.mymovie.ui.movie.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vickykdv.mymovie.data.repository.Repository
import com.vickykdv.mymovie.state.DetailMovieState
import com.vickykdv.mymovie.state.VideoState

class DetailMovieViewModel @ViewModelInject constructor(
        private val repository: Repository
): ViewModel() {

    val state : MutableLiveData<DetailMovieState> by lazy {
        MutableLiveData<DetailMovieState>()
    }

    val stateVideo : MutableLiveData<VideoState> by lazy {
        MutableLiveData<VideoState>()
    }

    fun getDetailMovie(movieId: Int) {
        repository.getDetailMovie(movieId, state)
    }

    fun getVideos(type : String, id: Int) {
        repository.getVideos(type, id, stateVideo)
    }


}