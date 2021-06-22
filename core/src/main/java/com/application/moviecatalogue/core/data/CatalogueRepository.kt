package com.application.moviecatalogue.core.data

import com.application.moviecatalogue.core.data.source.local.LocalDataSource
import com.application.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.application.moviecatalogue.core.data.source.remote.response.MovieDetailResponse
import com.application.moviecatalogue.core.data.source.remote.response.TvShowDetailResponse
import com.application.moviecatalogue.core.data.source.remote.response.api.ApiResponse
import com.application.moviecatalogue.core.domain.model.Movie
import com.application.moviecatalogue.core.domain.model.TvShow
import com.application.moviecatalogue.core.domain.repository.ICatalogueRepository
import com.application.moviecatalogue.core.utils.AppExecutors
import com.application.moviecatalogue.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogueRepository @Inject constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) :
        ICatalogueRepository {

    override fun getMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieDetailResponse>>(appExecutors) {
            public override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getListMovie().map {
                    DataMapper.mapEntitiesToDomainMovie(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                    data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieDetailResponse>>> =
                    remoteDataSource.getMovies()

            override suspend fun saveCallResult(data: List<MovieDetailResponse>) {
                val movieList = DataMapper.mapResponseToEntitiesMovie(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()
    }

    override fun getTvShows(): Flow<Resource<List<TvShow>>> {
        return object : NetworkBoundResource<List<TvShow>, List<TvShowDetailResponse>>(appExecutors) {
            public override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getListTvShow().map {
                    DataMapper.mapEntitiesToDomainTvShow(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                    data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowDetailResponse>>> =
                    remoteDataSource.getTvShows()

            override suspend fun saveCallResult(data: List<TvShowDetailResponse>) {
                val tvShowList = DataMapper.mapResponseToEntitiesTvShow(data)
                localDataSource.insertTvShow(tvShowList)
            }
        }.asFlow()
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getListFavoriteMovie().map {
            DataMapper.mapEntitiesToDomainMovie(it)
        }
    }

    override fun getFavoriteTvShow(): Flow<List<TvShow>> {
        return localDataSource.getListFavoriteTvShow().map {
            DataMapper.mapEntitiesToDomainTvShow(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntityMovie(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) {
        val tvShowEntity = DataMapper.mapDomainToEntityTvShow(tvShow)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShowEntity, state) }
    }
}