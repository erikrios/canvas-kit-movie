package io.erikrios.github.canvaskitmovie.di

import io.erikrios.github.canvaskitmovie.core.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<TvShowUseCase> { TvShowInteractor(get()) }
    factory<TrendingUseCase> { TrendingInteractor(get()) }
}