package com.vickykdv.mymovie.data.factory

import com.vickykdv.mymovie.data.factory.movie.PopularMovieDataFactory
import com.vickykdv.mymovie.data.factory.movie.SearchMovieDataFactory
import com.vickykdv.mymovie.data.factory.movie.TopRatedMovieDataFactory
import com.vickykdv.mymovie.data.factory.movie.UpcomingMovieDataFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class Factory  @Inject constructor(
    val upcomingMovieDataFactory: UpcomingMovieDataFactory,
    val topRatedMovieDataFactory: TopRatedMovieDataFactory,
    val popularMovieDataFactory: PopularMovieDataFactory,
    val searchMovieDataFactory: SearchMovieDataFactory,
)