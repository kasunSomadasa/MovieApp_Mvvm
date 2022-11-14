package com.krs.movie.ui.movie.viewmodel

import androidx.lifecycle.*
import com.krs.movie.data.model.movie.MovieResponse
import com.krs.movie.repository.MovieRepository
import com.krs.movie.utils.NoInternetException
import com.krs.movie.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    // live data for movie list
    private val movieMutableLiveData: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val movieLiveData: LiveData<Resource<MovieResponse>>
        get() = movieMutableLiveData

    // request popular movie list from api and post it to the live data
    fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        movieMutableLiveData.postValue(Resource.Loading())
        try {
            val result = movieRepository.getPopularMovies()
            movieMutableLiveData.postValue(result)
        } catch (e: NoInternetException) {
            movieMutableLiveData.postValue(Resource.Error(e.message.toString()))
        } catch (e: Exception) {
            movieMutableLiveData.postValue(Resource.Error(e.message.toString()))
        }
    }


}