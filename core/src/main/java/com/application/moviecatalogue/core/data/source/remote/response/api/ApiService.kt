package com.application.moviecatalogue.core.data.source.remote.response.api

import com.application.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.application.moviecatalogue.core.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String
    ): MovieResponse

    @GET("discover/tv")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String
    ) : TvShowResponse
}