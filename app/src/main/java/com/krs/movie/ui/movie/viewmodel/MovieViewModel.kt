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
    private val _movieMutableLiveData: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val movieLiveData: LiveData<Resource<MovieResponse>>
        get() = _movieMutableLiveData

    // request popular movie list from api and post it to the live data
    fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        _movieMutableLiveData.postValue(Resource.Loading())
        try {
            val result = movieRepository.getPopularMovies()
            _movieMutableLiveData.postValue(result)
        } catch (e: NoInternetException) {
            _movieMutableLiveData.postValue(Resource.Error(e.message.toString()))
        } catch (e: Exception) {
            _movieMutableLiveData.postValue(Resource.Error(e.message.toString()))
        }
    }


}