package com.krs.movie.data.model.tvshow


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TvShow(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?
): Serializable