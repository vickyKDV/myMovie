package com.vickykdv.mymovie.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import com.vickykdv.mymovie.data.factory.Factory
import com.vickykdv.mymovie.data.factory.movie.PopularMovieDataFactory
import com.vickykdv.mymovie.data.factory.movie.SearchMovieDataFactory
import com.vickykdv.mymovie.data.factory.movie.TopRatedMovieDataFactory
import com.vickykdv.mymovie.data.factory.movie.UpcomingMovieDataFactory
import com.vickykdv.mymovie.data.source.movie.PopularMovieDataSource
import com.vickykdv.mymovie.data.source.movie.SearchMovieDataSource
import com.vickykdv.mymovie.data.source.movie.TopRatedMovieDataSource
import com.vickykdv.mymovie.data.source.movie.UpcomingMovieDataSource
import javax.inject.Singleton



@Module
@InstallIn(ApplicationComponent::class)
class DataFactoryModule {

    @Provides
    @Singleton
    fun provideFactory(
        upcomingMovieDataFactory: UpcomingMovieDataFactory,
        topRatedMovieDataFactory: TopRatedMovieDataFactory,
        popularMovieDataFactory: PopularMovieDataFactory,
        searchMovieDataFactory: SearchMovieDataFactory,
    ) : Factory = Factory(
        upcomingMovieDataFactory,
        topRatedMovieDataFactory,
        popularMovieDataFactory,
        searchMovieDataFactory,
    )

    @Provides
    @Singleton
    fun provideUpcomingMovieFactory(
            upcomingMovieDataSource: UpcomingMovieDataSource
    ) : UpcomingMovieDataFactory = UpcomingMovieDataFactory(upcomingMovieDataSource)

    @Provides
    @Singleton
    fun provideTopRatedMovieFactory(
            topRatedMovieDataSource: TopRatedMovieDataSource
    ) : TopRatedMovieDataFactory = TopRatedMovieDataFactory(topRatedMovieDataSource)

    @Provides
    @Singleton
    fun providePopularMovieFactory(
            popularMovieDataSource: PopularMovieDataSource
    ) : PopularMovieDataFactory = PopularMovieDataFactory(popularMovieDataSource)

    @Provides
    @Singleton
    fun provideSearchMovieFactory(
            searchMovieDataSource: SearchMovieDataSource
    ) : SearchMovieDataFactory = SearchMovieDataFactory(searchMovieDataSource)


}