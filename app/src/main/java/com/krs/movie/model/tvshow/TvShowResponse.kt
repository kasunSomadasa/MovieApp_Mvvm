package com.krs.movie.model.tvshow


import com.google.gson.annotations.SerializedName
import com.krs.tmdbclient.data.model.tvshow.TvShow

data class TvShowResponse(
    @SerializedName("results")
    val tvShows: List<TvShow>,
)