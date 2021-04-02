package io.erikrios.github.canvaskitmovie.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.erikrios.github.canvaskitmovie.BuildConfig
import io.erikrios.github.canvaskitmovie.data.repository.CinemaRepository
import io.erikrios.github.canvaskitmovie.data.repository.CinemaRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideCinemaRepositoryImpl() = CinemaRepositoryImpl() as CinemaRepository
}