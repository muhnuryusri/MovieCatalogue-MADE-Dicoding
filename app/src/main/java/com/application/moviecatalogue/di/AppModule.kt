package com.application.moviecatalogue.di

import com.application.moviecatalogue.core.data.CatalogueRepository
import com.application.moviecatalogue.core.domain.usecase.CatalogueInteractor
import com.application.moviecatalogue.core.domain.usecase.CatalogueUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCatalogueUseCase(catalogueRepository: CatalogueRepository): CatalogueUseCase =
        CatalogueInteractor(catalogueRepository)

}