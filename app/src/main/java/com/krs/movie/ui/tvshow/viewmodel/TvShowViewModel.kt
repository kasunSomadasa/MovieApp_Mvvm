package com.krs.movie.ui.tvshow.viewmodel

import androidx.lifecycle.*
import com.krs.movie.data.model.tvshow.TvShowResponse
import com.krs.movie.repository.TvShowRepository
import com.krs.movie.utils.NoInternetException
import com.krs.movie.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowViewModel constructor(
    private val tvShowRepository: TvShowRepository
) : ViewModel() {

    // live data for tv show list
    private val tvShowMutableLiveData: MutableLiveData<Resource<TvShowResponse>> = MutableLiveData()
    val tvShowLiveData: LiveData<Resource<TvShowResponse>>
        get() = tvShowMutableLiveData

    // request popular tv show list from api and post it to the live data
    fun getPopularTvShow() = viewModelScope.launch(Dispatchers.IO) {
        tvShowMutableLiveData.postValue(Resource.Loading())
        try {
            val result = tvShowRepository.getPopularTVShows()
            tvShowMutableLiveData.postValue(result)
        } catch (e: NoInternetException) {
            tvShowMutableLiveData.postValue(Resource.Error(e.message.toString()))
        } catch (e: Exception) {
            tvShowMutableLiveData.postValue(Resource.Error(e.message.toString()))
        }
    }


}