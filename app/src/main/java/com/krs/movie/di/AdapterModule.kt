package com.krs.movie.di

import com.krs.movie.ui.movie.MovieAdapter
import com.krs.movie.ui.tvshow.TvShowAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AdapterModule {

    @Singleton
    @Provides
    fun provideMovieAdapter() : MovieAdapter {
        return MovieAdapter()
    }

    @Singleton
    @Provides
    fun provideTvShowAdapter() : TvShowAdapter {
        return TvShowAdapter()
    }
}