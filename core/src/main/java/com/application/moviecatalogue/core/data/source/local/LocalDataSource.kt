package com.application.moviecatalogue.core.data.source.local

import com.application.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.application.moviecatalogue.core.data.source.local.entity.TvShowEntity
import com.application.moviecatalogue.core.data.source.local.room.CatalogueDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val mCatalogueDao: CatalogueDao) {

    fun getListMovie(): Flow<List<MovieEntity>> = mCatalogueDao.getListMovie()

    fun getListTvShow(): Flow<List<TvShowEntity>> = mCatalogueDao.getListTvShow()

    fun getListFavoriteMovie(): Flow<List<MovieEntity>> = mCatalogueDao.getListFavoriteMovie()

    fun getListFavoriteTvShow(): Flow<List<TvShowEntity>> = mCatalogueDao.getListFavoriteTvShow()

    suspend fun insertMovie(movieList: List<MovieEntity>) = mCatalogueDao.insertMovie(movieList)

    suspend fun insertTvShow(tvShowList: List<TvShowEntity>) = mCatalogueDao.insertTvShow(tvShowList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mCatalogueDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mCatalogueDao.updateTvShow(tvShow)
    }
}