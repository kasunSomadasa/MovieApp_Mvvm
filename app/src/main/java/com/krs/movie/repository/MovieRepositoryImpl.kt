package com.krs.movie.repository

import com.krs.movie.data.network.MovieApiService
import com.krs.movie.data.model.movie.MovieResponse
import com.krs.movie.utils.Resource
import retrofit2.Response

class MovieRepositoryImpl constructor(
    private val movieApiService: MovieApiService
) : MovieRepository {

    /**
     * return popular movie response as resource
     */
    override suspend fun getPopularMovies(): Resource<MovieResponse> {
        return responseToResource(movieApiService.getPopularMovies())
    }

    /**
     * convert response to resource type
     */
    private fun responseToResource(response: Response<MovieResponse>): Resource<MovieResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

}