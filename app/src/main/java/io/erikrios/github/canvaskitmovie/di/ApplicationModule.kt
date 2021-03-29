package io.erikrios.github.canvaskitmovie.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.erikrios.github.canvaskitmovie.data.repository.CinemaRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideCinemaRepositoryImpl() = CinemaRepositoryImpl()
}