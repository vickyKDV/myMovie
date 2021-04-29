package com.vickykdv.mymovie.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.vickykdv.mymovie.data.model.detailmovie.ResponseDetailMovie
import com.vickykdv.mymovie.data.model.movie.ResponseMovie
import com.vickykdv.mymovie.data.model.videos.ResponseVideo



interface ApiService {

    //Movie
    @GET("movie/now_playing")
    fun getNowPlayingMovie() : Single<ResponseMovie>

    @GET("movie/upcoming")
    fun getUpcomingMovie() : Single<ResponseMovie>

    @GET("movie/popular")
    fun getPopularMovie() : Single<ResponseMovie>

    @GET("movie/top_rated")
    fun getTopRatedMovie() : Single<ResponseMovie>

    @GET("movie/{movieId}")
    fun getDetailMovie(
            @Path("movieId") movieId: Int
    ) : Single<ResponseDetailMovie>

    @GET("movie/{movieType}")
    fun getAllMovie(
        @Path("movieType") movieType: String,
        @Query("page") page : Int
    ) : Single<ResponseMovie>

    @GET("search/movie")
    fun searchMovie(
            @Query("query") query: String,
            @Query("page") page : Int
    ) : Single<ResponseMovie>

    @GET("{type}/{id}/videos")
    fun getVideos(
            @Path("type") type: String,
            @Path("id") id: Int
    ) : Single<ResponseVideo>

}