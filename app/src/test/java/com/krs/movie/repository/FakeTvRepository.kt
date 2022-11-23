package com.krs.movie.repository

import com.krs.movie.data.model.tvshow.TvShow
import com.krs.movie.data.model.tvshow.TvShowResponse
import com.krs.movie.utils.Resource

class FakeTvRepository : TvShowRepository{
    private val tvList = mutableListOf<TvShow>()

    init {
        tvList.add(TvShow(1, "name1","overview1", "/path/1", "2022/12/10" ))
        tvList.add(TvShow(2, "name2","overview2","/path/2", "2022/12/12" ))
    }

    override suspend fun getPopularTVShows(): Resource<TvShowResponse> {
        return Resource.Success(TvShowResponse(tvList))
    }
}