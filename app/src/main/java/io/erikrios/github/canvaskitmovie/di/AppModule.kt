package io.erikrios.github.canvaskitmovie.di

import io.erikrios.github.canvaskitmovie.core.domain.usecase.*
import io.erikrios.github.canvaskitmovie.details.MovieDetailsViewModel
import io.erikrios.github.canvaskitmovie.details.TrendingDetailsViewModel
import io.erikrios.github.canvaskitmovie.details.TvShowDetailsViewModel
import io.erikrios.github.canvaskitmovie.favorites.FavoriteMoviesViewModel
import io.erikrios.github.canvaskitmovie.favorites.FavoriteTrendingsViewModel
import io.erikrios.github.canvaskitmovie.favorites.FavoriteTvShowsViewModel
import io.erikrios.github.canvaskitmovie.movies.DiscoverMoviesViewModel
import io.erikrios.github.canvaskitmovie.trending.DiscoverTrendingViewModel
import io.erikrios.github.canvaskitmovie.tvshows.DiscoverTvShowsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<TvShowUseCase> { TvShowInteractor(get()) }
    factory<TrendingUseCase> { TrendingInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DiscoverMoviesViewModel(get()) }
    viewModel { DiscoverTvShowsViewModel(get()) }
    viewModel { DiscoverTrendingViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
    viewModel { TvShowDetailsViewModel(get()) }
    viewModel { TrendingDetailsViewModel(get()) }
    viewModel { FavoriteMoviesViewModel(get()) }
    viewModel { FavoriteTvShowsViewModel(get()) }
    viewModel { FavoriteTrendingsViewModel(get()) }
}