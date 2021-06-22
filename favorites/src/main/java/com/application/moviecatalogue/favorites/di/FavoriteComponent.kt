package com.application.moviecatalogue.favorites.di

import android.content.Context
import com.application.moviecatalogue.di.FavoritesDependencies
import com.application.moviecatalogue.favorites.favorite.movie.FavoriteMovieFragment
import com.application.moviecatalogue.favorites.favorite.tvshow.FavoriteTvShowFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoritesDependencies::class])
interface FavoritesComponent {

    fun inject(favoriteMovieFragment: FavoriteMovieFragment)
    fun inject(favoriteTvShowsFragment: FavoriteTvShowFragment)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoritesDependencies: FavoritesDependencies): Builder
        fun build(): FavoritesComponent
    }

}