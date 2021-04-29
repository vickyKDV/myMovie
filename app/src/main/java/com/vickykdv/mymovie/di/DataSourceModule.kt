package com.vickykdv.mymovie.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import com.vickykdv.mymovie.data.network.ApiService
import com.vickykdv.mymovie.data.source.movie.PopularMovieDataSource
import com.vickykdv.mymovie.data.source.movie.SearchMovieDataSource
import com.vickykdv.mymovie.data.source.movie.TopRatedMovieDataSource
import com.vickykdv.mymovie.data.source.movie.UpcomingMovieDataSource
import javax.inject.Singleton



@Module
@InstallIn(ApplicationComponent::class)
class DataSourceModule {
    @Provides
    @Singleton
    fun provideUpcomingMovieDataSource(
        apiservice: ApiService
    ) : UpcomingMovieDataSource = UpcomingMovieDataSource(apiservice)

    @Provides
    @Singleton
    fun provideTopRatedDataSource(
            apiservice: ApiService
    ) : TopRatedMovieDataSource = TopRatedMovieDataSource(apiservice)

    @Provides
    @Singleton
    fun providePopularMovieDataSource(
            apiservice: ApiService
    ) : PopularMovieDataSource = PopularMovieDataSource(apiservice)

    @Provides
    @Singleton
    fun provideSearchMovieDataSource(
            apiservice: ApiService
    ) : SearchMovieDataSource = SearchMovieDataSource(apiservice)

}