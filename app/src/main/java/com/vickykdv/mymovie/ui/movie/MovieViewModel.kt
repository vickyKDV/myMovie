package com.vickykdv.mymovie.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vickykdv.mymovie.data.repository.Repository
import com.vickykdv.mymovie.state.MovieState



class MovieViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val stateUpcoming : MutableLiveData<MovieState> by lazy {
        MutableLiveData<MovieState>()
    }

    val statePopular : MutableLiveData<MovieState> by lazy {
        MutableLiveData<MovieState>()
    }

    val stateTopRated : MutableLiveData<MovieState> by lazy {
        MutableLiveData<MovieState>()
    }

    fun getUpcomingMovie() {
        repository.getUpcomingMovie(stateUpcoming)
    }

    fun getPopularMovie() {
        repository.getPopularMovie(statePopular)
    }

    fun getTopRatedMovie() {
        repository.getTopRatedMovie(stateTopRated)
    }
}