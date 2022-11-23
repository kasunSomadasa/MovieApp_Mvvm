package com.krs.movie.repository

import com.krs.movie.data.model.tvshow.TvShowResponse
import com.krs.movie.utils.Resource

interface TvShowRepository {
    suspend fun getPopularTVShows(): Resource<TvShowResponse>
}