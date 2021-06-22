package com.application.moviecatalogue.favorites.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.application.moviecatalogue.core.domain.usecase.CatalogueUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor (catalogueUseCase: CatalogueUseCase): ViewModel() {
    val favoriteMovie = catalogueUseCase.getFavoriteMovie().asLiveData()
    val favoriteTvShow = catalogueUseCase.getFavoriteTvShow().asLiveData()
}