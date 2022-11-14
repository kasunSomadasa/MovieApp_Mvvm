package com.krs.movie.repository

import com.krs.movie.data.model.movie.MovieResponse
import com.krs.movie.utils.Resource

interface MovieRepository {
    suspend fun getPopularMovies(): Resource<MovieResponse>
}