package com.vickykdv.mymovie.ui.movie.upcoming

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.vickykdv.mymovie.data.model.movie.DataMovie
import com.vickykdv.mymovie.data.repository.Repository
import com.vickykdv.mymovie.state.MovieState

class UpcomingMovieViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val state : MutableLiveData<MovieState> by lazy {
        MutableLiveData<MovieState>()
    }

    val data : MutableLiveData<PagedList<DataMovie>> by lazy {
        MutableLiveData<PagedList<DataMovie>>()
    }

    fun getMovie() {
        repository.getAllUpcomingMovie(state, data)
    }
}