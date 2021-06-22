package com.application.moviecatalogue.core.domain.usecase

import com.application.moviecatalogue.core.domain.model.Movie
import com.application.moviecatalogue.core.domain.model.TvShow
import com.application.moviecatalogue.core.domain.repository.ICatalogueRepository
import javax.inject.Inject

class CatalogueInteractor @Inject constructor(private val catalogueRepository: ICatalogueRepository): CatalogueUseCase {
    override fun getMovies() = catalogueRepository.getMovies()

    override fun getTvShows() = catalogueRepository.getTvShows()

    override fun getFavoriteMovie() = catalogueRepository.getFavoriteMovie()

    override fun getFavoriteTvShow() = catalogueRepository.getFavoriteTvShow()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = catalogueRepository.setFavoriteMovie(movie, state)

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) = catalogueRepository.setFavoriteTvShow(tvShow, state)

}