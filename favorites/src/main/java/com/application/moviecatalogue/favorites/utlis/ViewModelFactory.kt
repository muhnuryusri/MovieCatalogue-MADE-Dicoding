package com.application.moviecatalogue.favorites.utlis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.moviecatalogue.core.domain.usecase.CatalogueUseCase
import com.application.moviecatalogue.favorites.favorite.FavoriteViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val catalogueUseCase: CatalogueUseCase): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when{
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(catalogueUseCase) as T
            }
            else -> throw Throwable("Unkwon ViewModel Class: ${modelClass.name}")
        }
}