package com.krs.movie.di

import com.krs.movie.repository.TvShowRepository
import com.krs.movie.ui.tvshow.viewmodel.TvShowViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class TvShowModule {

    @ActivityScoped
    @Provides
    fun provideTvShowViewModelFactory(
        tvShowRepository : TvShowRepository
    ): TvShowViewModelFactory {
        return TvShowViewModelFactory(
            tvShowRepository
        )
    }
}