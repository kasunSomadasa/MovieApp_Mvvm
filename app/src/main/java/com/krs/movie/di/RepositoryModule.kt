package com.krs.movie.di

import com.krs.movie.data.network.MovieApiService
import com.krs.movie.repository.MovieRepository
import com.krs.movie.repository.MovieRepositoryImpl
import com.krs.movie.repository.TvShowRepository
import com.krs.movie.repository.TvShowRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(movieApiService: MovieApiService) : MovieRepository {
        return MovieRepositoryImpl(movieApiService)
    }

    @Singleton
    @Provides
    fun provideTvShowRepository(movieApiService: MovieApiService) : TvShowRepository {
        return TvShowRepositoryImpl(movieApiService)
    }
}