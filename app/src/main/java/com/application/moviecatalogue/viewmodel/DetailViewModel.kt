package com.application.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.application.moviecatalogue.core.domain.model.Movie
import com.application.moviecatalogue.core.domain.model.TvShow
import com.application.moviecatalogue.core.domain.usecase.CatalogueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newState: Boolean) = catalogueUseCase.setFavoriteMovie(movie, newState)

    fun setFavoriteTvShow(tvShow: TvShow, newState: Boolean) = catalogueUseCase.setFavoriteTvShow(tvShow, newState)
}