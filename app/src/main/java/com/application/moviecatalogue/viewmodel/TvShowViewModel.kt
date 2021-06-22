package com.application.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.application.moviecatalogue.core.domain.usecase.CatalogueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val tvShow = catalogueUseCase.getTvShows().asLiveData()
}