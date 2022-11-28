package com.krs.movie.di

import android.app.Application
import com.krs.movie.BuildConfig
import com.krs.movie.data.network.MovieApiService
import com.krs.movie.data.network.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    companion object {
        private const val CONNECT_TIMEOUT = 20L
        private const val READ_TIMEOUT = 60L
        private const val WRITE_TIMEOUT = 120L
    }

    @Singleton
    @Provides
    fun provideNetworkConnectionInterceptor(app: Application) : NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(app)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(networkConnectionInterceptor: NetworkConnectionInterceptor): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(networkConnectionInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieApiService(retrofit: Retrofit) : MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }
}