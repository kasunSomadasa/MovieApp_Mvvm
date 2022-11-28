package com.krs.movie.di

import com.krs.movie.repository.MovieRepository
import com.krs.movie.ui.movie.viewmodel.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class MovieModule {

    @ActivityScoped
    @Provides
    fun provideMovieViewModelFactory(
        movieRepository: MovieRepository
    ): MovieViewModelFactory {
        return MovieViewModelFactory(
            movieRepository
        )
    }
}