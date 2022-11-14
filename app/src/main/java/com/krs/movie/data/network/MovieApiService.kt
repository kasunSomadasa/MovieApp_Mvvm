package com.krs.movie.data.network

import com.krs.movie.BuildConfig
import com.krs.movie.data.model.movie.MovieResponse
import com.krs.movie.data.model.tvshow.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY)
    : Response<MovieResponse>

    @GET("tv/popular")
    suspend fun getPopularTVShows(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY)
    : Response<TvShowResponse>
}