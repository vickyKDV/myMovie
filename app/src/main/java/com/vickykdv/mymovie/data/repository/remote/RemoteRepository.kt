package com.vickykdv.mymovie.data.repository.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.vickykdv.mymovie.data.database.RoomDb
import com.vickykdv.mymovie.data.database.model.MovieEntity
import com.vickykdv.mymovie.data.database.model.TvShowEntity
import com.vickykdv.mymovie.state.*
import com.vickykdv.mymovie.data.factory.Factory
import com.vickykdv.mymovie.data.model.movie.DataMovie
import com.vickykdv.mymovie.data.network.ApiService
import com.vickykdv.mymovie.data.repository.Repository
import javax.inject.Inject



class RemoteRepository @Inject constructor(
    private val apiService: ApiService,
    private val config : PagedList.Config,
    private val factory: Factory
) : Repository {

    var disposable: CompositeDisposable = CompositeDisposable()

    override fun getDisposible(): CompositeDisposable = disposable

    override fun getNowPlayingMovie(callback: MutableLiveData<MovieState>) {
        apiService.getNowPlayingMovie()
                .map<MovieState>(MovieState::Result)
                .onErrorReturn(MovieState::Error)
                .toFlowable()
                .startWith(MovieState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add }
    }

    override fun getUpcomingMovie(callback: MutableLiveData<MovieState>) {
        apiService.getUpcomingMovie()
                .map<MovieState>(MovieState::Result)
                .onErrorReturn(MovieState::Error)
                .toFlowable()
                .startWith(MovieState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add }
    }

    override fun getPopularMovie(callback: MutableLiveData<MovieState>) {
        apiService.getPopularMovie()
                .map<MovieState>(MovieState::Result)
                .onErrorReturn(MovieState::Error)
                .toFlowable()
                .startWith(MovieState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add }
    }

    override fun getTopRatedMovie(callback: MutableLiveData<MovieState>) {
        apiService.getTopRatedMovie()
                .map<MovieState>(MovieState::Result)
                .onErrorReturn(MovieState::Error)
                .toFlowable()
                .startWith(MovieState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add }
    }

    override fun getDetailMovie(movieId: Int, callback: MutableLiveData<DetailMovieState>) {
        apiService.getDetailMovie(movieId)
                .map<DetailMovieState>(DetailMovieState::Result)
                .onErrorReturn(DetailMovieState::Error)
                .toFlowable()
                .startWith(DetailMovieState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add }
    }

    override fun getAllUpcomingMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            LivePagedListBuilder(
                factory.upcomingMovieDataFactory.also {
                    it.liveData = callback
                },
                config
            ).build().observeForever(data::postValue)
        }
    }

    override fun getAllTopRatedMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            LivePagedListBuilder(
                factory.topRatedMovieDataFactory.also {
                    it.liveData = callback
                },
                config
            ).build().observeForever(data::postValue)
        }
    }

    override fun getAllPopularMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            LivePagedListBuilder(
                factory.popularMovieDataFactory.also {
                    it.liveData = callback
                },
                config
            ).build().observeForever(data::postValue)
        }
    }

    override fun searchMovie(query: String, callback: MutableLiveData<MovieState>, data: MutableLiveData<PagedList<DataMovie>>) {
        CoroutineScope(Dispatchers.Main).launch {
            LivePagedListBuilder(
                    factory.searchMovieDataFactory.also {
                        it.liveData = callback
                        it.keyword = query
                    },
                    config
            ).build().observeForever(data::postValue)
        }
    }

    override fun addDataMovie(data: MovieEntity) {
        throw UnsupportedOperationException()
    }

    override fun checkDataMovie(data: MovieEntity): List<MovieEntity> {
        throw UnsupportedOperationException()
    }

    override fun deleteDataMovie(data: MovieEntity) {
        throw UnsupportedOperationException()
    }

    override fun addDataTvShow(data: TvShowEntity) {
        throw UnsupportedOperationException()
    }

    override fun checkDataTvShow(data: TvShowEntity): List<TvShowEntity> {
        throw UnsupportedOperationException()
    }

    override fun deleteDataTvShow(data: TvShowEntity) {
        throw UnsupportedOperationException()
    }

    override fun getVideos(type: String, id: Int, callback: MutableLiveData<VideoState>) {
        apiService.getVideos(type, id)
                .map<VideoState>(VideoState::Result)
                .onErrorReturn(VideoState::Error)
                .toFlowable()
                .startWith(VideoState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add }
    }

    override fun getDatabase(): RoomDb {
        throw UnsupportedOperationException()
    }
}