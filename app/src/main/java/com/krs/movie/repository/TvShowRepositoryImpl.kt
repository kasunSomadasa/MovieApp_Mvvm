package com.krs.movie.repository

import com.krs.movie.data.model.tvshow.TvShowResponse
import com.krs.movie.data.network.MovieApiService
import com.krs.movie.utils.Resource
import retrofit2.Response

class TvShowRepositoryImpl constructor(
    private val movieApiService: MovieApiService
) : TvShowRepository {

    /**
     * return popular tv show response as resource
     */
    override suspend fun getPopularTVShows(): Resource<TvShowResponse> {
        return responseToResource(movieApiService.getPopularTVShows())
    }


    /**
     * convert response to resource type
     */
    private fun responseToResource(response: Response<TvShowResponse>): Resource<TvShowResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}