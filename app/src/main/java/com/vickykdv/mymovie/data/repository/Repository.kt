package com.vickykdv.mymovie.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import com.vickykdv.mymovie.data.database.RoomDb
import com.vickykdv.mymovie.data.database.model.MovieEntity
import com.vickykdv.mymovie.data.database.model.TvShowEntity
import com.vickykdv.mymovie.state.*
import com.vickykdv.mymovie.data.model.movie.DataMovie



interface Repository {
    //Movies
    fun getNowPlayingMovie(callback : MutableLiveData<MovieState>)
    fun getUpcomingMovie(callback : MutableLiveData<MovieState>)
    fun getPopularMovie(callback : MutableLiveData<MovieState>)
    fun getTopRatedMovie(callback : MutableLiveData<MovieState>)
    fun getDetailMovie(movieId: Int, callback : MutableLiveData<DetailMovieState>)

    //See All Movie
    fun getAllUpcomingMovie(
        callback : MutableLiveData<MovieState>,
        data : MutableLiveData<PagedList<DataMovie>>
    )
    fun getAllTopRatedMovie(
        callback : MutableLiveData<MovieState>,
        data : MutableLiveData<PagedList<DataMovie>>
    )
    fun getAllPopularMovie(
        callback : MutableLiveData<MovieState>,
        data : MutableLiveData<PagedList<DataMovie>>
    )
    fun searchMovie(
        query: String,
        callback : MutableLiveData<MovieState>,
        data : MutableLiveData<PagedList<DataMovie>>
    )




    fun addDataMovie(data : MovieEntity)
    fun checkDataMovie(data: MovieEntity) : List<MovieEntity>
    fun deleteDataMovie(data : MovieEntity)
    fun addDataTvShow(data : TvShowEntity)
    fun checkDataTvShow(data : TvShowEntity) : List<TvShowEntity>
    fun deleteDataTvShow(data : TvShowEntity)

    //Videos
    fun getVideos(
            type: String,
            id: Int,
            callback : MutableLiveData<VideoState>
    )

    fun getDisposible() : CompositeDisposable
    fun getDatabase() : RoomDb
}