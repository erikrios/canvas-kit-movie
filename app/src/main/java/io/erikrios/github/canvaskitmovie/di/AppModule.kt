package io.erikrios.github.canvaskitmovie.di

import io.erikrios.github.canvaskitmovie.core.domain.usecase.*
import io.erikrios.github.canvaskitmovie.details.MovieDetailsViewModel
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
}