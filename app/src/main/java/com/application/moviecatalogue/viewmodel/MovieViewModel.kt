package com.application.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.application.moviecatalogue.core.domain.usecase.CatalogueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val movie = catalogueUseCase.getMovies().asLiveData()
}