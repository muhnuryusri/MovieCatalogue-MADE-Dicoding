package com.application.moviecatalogue.core.data.source.local.room

import androidx.room.*
import com.application.moviecatalogue.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogueDao {
    @Query("SELECT * FROM movie_entities")
    fun getListMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tv_show_entities")
    fun getListTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM movie_entities WHERE isFavorite = 1")
    fun getListFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tv_show_entities WHERE isFavorite = 1")
    fun getListFavoriteTvShow(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    suspend fun insertMovie(data: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    suspend fun insertTvShow(data: List<TvShowEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}