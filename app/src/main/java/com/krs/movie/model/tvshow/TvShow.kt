package com.krs.movie.model.tvshow


import com.google.gson.annotations.SerializedName

data class TvShow(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
)