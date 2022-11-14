package com.krs.movie.data.model.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("results")
    val tvShows: List<TvShow>,
)