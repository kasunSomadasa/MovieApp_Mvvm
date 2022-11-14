package com.krs.movie.repository

import com.krs.movie.data.model.movie.Movie
import com.krs.movie.data.model.movie.MovieResponse
import com.krs.movie.utils.Resource

class FakeMovieRepository : MovieRepository{
    private val movieList = mutableListOf<Movie>()

    init {
        movieList.add(Movie(1, "overview1", "/path/1", "2022/12/10", "title1"))
        movieList.add(Movie(2, "overview2", "/path/2", "2022/12/12", "title2"))
    }

    override suspend fun getPopularMovies(): Resource<MovieResponse> {
        return Resource.Success(MovieResponse(movieList))
    }
}