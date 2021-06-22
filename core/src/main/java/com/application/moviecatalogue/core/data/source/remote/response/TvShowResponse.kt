package com.application.moviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @field:SerializedName("results")
    val results: ArrayList<TvShowDetailResponse>
)