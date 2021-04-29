package com.vickykdv.mymovie.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import com.vickykdv.mymovie.data.database.RoomDb
import com.vickykdv.mymovie.data.database.model.MovieEntity
import com.vickykdv.mymovie.data.database.model.TvShowEntity
import com.vickykdv.mymovie.state.*
import com.vickykdv.mymovie.data.model.movie.DataMovie
import com.vickykdv.mymovie.data.repository.local.LocalRepository
import com.vickykdv.mymovie.data.repository.remote.RemoteRepository
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class DataRepository @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : Repository {

    override fun getNowPlayingMovie(callback: MutableLiveData<MovieState>){
        remoteRepository.getNowPlayingMovie(callback)
    }

    override fun getUpcomingMovie(callback: MutableLiveData<MovieState>){
        remoteRepository.getUpcomingMovie(callback)
    }

    override fun getPopularMovie(callback: MutableLiveData<MovieState>){
        remoteRepository.getPopularMovie(callback)
    }

    override fun getTopRatedMovie(callback: MutableLiveData<MovieState>){
        remoteRepository.getTopRatedMovie(callback)
    }

    override fun getDetailMovie(
            movieId: Int,
            callback: MutableLiveData<DetailMovieState>
    ){
        remoteRepository.getDetailMovie(movieId, callback)
    }

    override fun getAllUpcomingMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ){
        remoteRepository.getAllUpcomingMovie(callback, data)
    }

    override fun getAllTopRatedMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ){
        remoteRepository.getAllTopRatedMovie(callback, data)
    }

    override fun getAllPopularMovie(
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ){
        remoteRepository.getAllPopularMovie(callback, data)
    }

    override fun searchMovie(
        query: String,
        callback: MutableLiveData<MovieState>,
        data: MutableLiveData<PagedList<DataMovie>>
    ) {
        remoteRepository.searchMovie(query, callback, data)
    }



    override fun addDataMovie(data: MovieEntity) {
        localRepository.addDataMovie(data)
    }

    override fun checkDataMovie(data: MovieEntity): List<MovieEntity> {
        return localRepository.checkDataMovie(data)
    }

    override fun deleteDataMovie(data: MovieEntity) {
        localRepository.deleteDataMovie(data)
    }

    override fun addDataTvShow(data: TvShowEntity) {
        localRepository.addDataTvShow(data)
    }

    override fun checkDataTvShow(data: TvShowEntity): List<TvShowEntity> {
        return localRepository.checkDataTvShow(data)
    }

    override fun deleteDataTvShow(data: TvShowEntity) {
        localRepository.deleteDataTvShow(data)
    }

    override fun getVideos(
            type: String,
            id: Int,
            callback: MutableLiveData<VideoState>
    ) {
        remoteRepository.getVideos(type, id, callback)
    }

    override fun getDisposible(): CompositeDisposable {
        return remoteRepository.getDisposible()
    }

    override fun getDatabase(): RoomDb {
        return localRepository.getDatabase()
    }
}