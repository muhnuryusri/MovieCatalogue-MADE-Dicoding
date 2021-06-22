package com.application.moviecatalogue.core.data.source.remote

import android.util.Log
import com.application.moviecatalogue.core.BuildConfig.API_KEY
import com.application.moviecatalogue.core.data.source.remote.response.*
import com.application.moviecatalogue.core.data.source.remote.response.api.ApiResponse
import com.application.moviecatalogue.core.data.source.remote.response.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val  apiService: ApiService){

    suspend fun getMovies(): Flow<ApiResponse<List<MovieDetailResponse>>> {
        return flow {
            try {
                val response = apiService.getMovies(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShows(): Flow<ApiResponse<List<TvShowDetailResponse>>> {
        return flow {
            try {
                val response = apiService.getTvShows(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}