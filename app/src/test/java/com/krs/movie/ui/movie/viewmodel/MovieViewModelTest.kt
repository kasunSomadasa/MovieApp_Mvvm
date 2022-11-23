package com.krs.movie.ui.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.krs.movie.data.model.movie.Movie
import com.krs.movie.repository.FakeMovieRepository
import com.krs.movie.utils.Resource
import com.krs.newsapp.data.getOrAwaitValue
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp() {
        val fakeMovieRepository = FakeMovieRepository()
        movieViewModel = MovieViewModel(fakeMovieRepository)
    }

    @After
    fun tearDown() {
    }

    /**
     * checking if the function return correct list
     */
    @Test
    fun getPopularMovies_returnCurrentList() {
        val movieList = mutableListOf<Movie>()

        movieList.add(Movie(1, "overview1", "/path/1", "2022/12/10", "title1"))
        movieList.add(Movie(2, "overview2", "/path/2", "2022/12/12", "title2"))

        movieViewModel.getPopularMovies()
        var response = movieViewModel.movieLiveData.getOrAwaitValue()
        when(response) {
            is Resource.Success -> {
                response.data?.let {
                    Truth.assertThat(it).isEqualTo(movieList)
                }
            }
        }
    }
}