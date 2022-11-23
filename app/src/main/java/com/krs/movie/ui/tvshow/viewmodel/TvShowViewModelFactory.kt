package com.krs.movie.ui.tvshow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krs.movie.repository.MovieRepository
import com.krs.movie.repository.TvShowRepository
import com.krs.movie.ui.movie.viewmodel.MovieViewModel

class TvShowViewModelFactory constructor(
    private val tvShowRepository: TvShowRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TvShowViewModel(tvShowRepository) as T
    }
}