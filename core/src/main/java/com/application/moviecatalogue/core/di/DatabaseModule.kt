package com.application.moviecatalogue.core.di

import android.content.Context
import androidx.room.Room
import com.application.moviecatalogue.core.data.source.local.room.CatalogueDao
import com.application.moviecatalogue.core.data.source.local.room.CatalogueDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CatalogueDatabase = Room.databaseBuilder(
            context,
            CatalogueDatabase::class.java, "favorites.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideCatalogueDao(database: CatalogueDatabase): CatalogueDao =
            database.catalogueDao()

}