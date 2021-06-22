package com.application.moviecatalogue.core.utils

import com.application.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.application.moviecatalogue.core.data.source.local.entity.TvShowEntity
import com.application.moviecatalogue.core.data.source.remote.response.MovieDetailResponse
import com.application.moviecatalogue.core.data.source.remote.response.TvShowDetailResponse
import com.application.moviecatalogue.core.domain.model.Movie
import com.application.moviecatalogue.core.domain.model.TvShow

object DataMapper {
    fun mapResponseToEntitiesMovie(input: List<MovieDetailResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movies = MovieEntity(
                    id = it.id,
                    title = it.title,
                    release = it.releaseDate,
                    score = it.voteAverage,
                    overview = it.overview,
                    poster = it.posterPath,
                    preview = it.backdropPath,
                    isFavorite = false
            )
            movieList.add(movies)
        }
        return movieList
    }

    fun mapEntitiesToDomainMovie(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                    id = it.id,
                    title = it.title,
                    release = it.release,
                    score = it.score,
                    overview = it.overview,
                    poster = it.poster,
                    preview = it.preview,
                    isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntityMovie(input: Movie) = MovieEntity(
            id = input.id,
            title = input.title,
            release = input.release,
            score = input.score,
            overview = input.overview,
            poster = input.poster,
            preview = input.preview,
            isFavorite = input.isFavorite
    )

    fun mapResponseToEntitiesTvShow(input: List<TvShowDetailResponse>): List<TvShowEntity> {
        val movieList = ArrayList<TvShowEntity>()
        input.map {
            val movies = TvShowEntity(
                    id = it.id,
                    title = it.name,
                    release = it.firsAirDate,
                    score = it.voteAverage,
                    overview = it.overview,
                    poster = it.posterPath,
                    preview = it.backdropPath,
                    isFavorite = false
            )
            movieList.add(movies)
        }
        return movieList
    }

    fun mapEntitiesToDomainTvShow(input: List<TvShowEntity>): List<TvShow> =
            input.map {
                TvShow(
                        id = it.id,
                        title = it.title,
                        release = it.release,
                        score = it.score,
                        overview = it.overview,
                        poster = it.poster,
                        preview = it.preview,
                        isFavorite = it.isFavorite
                )
            }

    fun mapDomainToEntityTvShow(input: TvShow) = TvShowEntity(
            id = input.id,
            title = input.title,
            release = input.release,
            score = input.score,
            overview = input.overview,
            poster = input.poster,
            preview = input.preview,
            isFavorite = input.isFavorite
    )
}