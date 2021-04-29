package com.vickykdv.mymovie.data.factory.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.vickykdv.mymovie.data.model.movie.DataMovie
import com.vickykdv.mymovie.data.source.movie.UpcomingMovieDataSource
import com.vickykdv.mymovie.state.MovieState
import javax.inject.Inject



class UpcomingMovieDataFactory @Inject constructor(
    private val upcomingMovieDataSource: UpcomingMovieDataSource
) : DataSource.Factory<Int, DataMovie>(){

    lateinit var liveData: MutableLiveData<MovieState>

    override fun create(): DataSource<Int, DataMovie> {
        return upcomingMovieDataSource.also {
            it.liveData = liveData
        }
    }
}