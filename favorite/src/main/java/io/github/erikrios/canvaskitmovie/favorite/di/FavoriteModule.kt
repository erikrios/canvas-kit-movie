package io.github.erikrios.canvaskitmovie.favorite.di

import io.github.erikrios.canvaskitmovie.favorite.FavoriteMoviesViewModel
import io.github.erikrios.canvaskitmovie.favorite.FavoriteTrendingsViewModel
import io.github.erikrios.canvaskitmovie.favorite.FavoriteTvShowsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMoviesViewModel(get()) }
    viewModel { FavoriteTvShowsViewModel(get()) }
    viewModel { FavoriteTrendingsViewModel(get()) }
}