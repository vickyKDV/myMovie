package com.vickykdv.mymovie.data.repository.local

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import com.vickykdv.mymovie.data.database.RoomDb
import com.vickykdv.mymovie.data.database.model.MovieEntity
import com.vickykdv.mymovie.data.database.model.TvShowEntity
import com.vickykdv.mymovie.state.*
import com.vickykdv.mymovie.data.model.movie.DataMovie
import com.vickykdv.mymovie.data.repository.Repository
import javax.inject.Inject



class LocalRepository @Inject constructor(
    private val database: RoomDb,
    private val config: PagedList.Config
) : Repository {

    var disposable: CompositeDisposable = CompositeDisposable()

    override fun getNowPlayingMovie(callback: MutableLiveData<MovieState>) {
        throw UnsupportedOperationException()
    }

    override fun getUpcomingMovie(callback: MutableLiveData<MovieState>) {
        throw UnsupportedOperationException()
    }

    override fun getPopularMovie(callback: MutableLiveData<MovieState>) {
        throw UnsupportedOperationException()
    }

    override fun getTopRatedMovie(callback: MutableLiveData<MovieState>) {
        throw UnsupportedOperationException()
    }

    override fun getDetailMovie(movieId: Int, callback: MutableLiveData<DetailMovieState>) {
        throw UnsupportedOperationException()
    }

    override fun getAllUpcomingMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        throw UnsupportedOperationException()
    }

    override fun getAllTopRatedMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        throw UnsupportedOperationException()
    }

    override fun getAllPopularMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        throw UnsupportedOperationException()
    }

    override fun searchMovie(
        query: String,
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        throw UnsupportedOperationException()
    }


    override fun addDataMovie(data: MovieEntity) {
        database.movie().add(data)
    }

    override fun checkDataMovie(data: MovieEntity): List<MovieEntity> {
        return database.movie().getDataById(data.id)
    }

    override fun deleteDataMovie(data: MovieEntity) {
        database.movie().delete(data)
    }

    override fun addDataTvShow(data: TvShowEntity) {
        database.tvShow().add(data)
    }

    override fun checkDataTvShow(data: TvShowEntity): List<TvShowEntity> {
        return database.tvShow().getDataById(data.id)
    }

    override fun deleteDataTvShow(data: TvShowEntity) {
        database.tvShow().delete(data)
    }

    override fun getVideos(type: String, id: Int, callback: MutableLiveData<VideoState>) {
        throw UnsupportedOperationException()
    }

    override fun getDisposible(): CompositeDisposable = disposable

    override fun getDatabase(): RoomDb = database
}