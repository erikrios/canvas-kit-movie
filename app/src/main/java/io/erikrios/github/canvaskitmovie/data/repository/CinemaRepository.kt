package io.erikrios.github.canvaskitmovie.data.repository

import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.data.source.DataSource
import io.erikrios.github.canvaskitmovie.utils.Resource
import javax.inject.Inject
import javax.inject.Named

abstract class CinemaRepository {
    @Inject
    @Named("localeDataSource")
    protected lateinit var localeDataSource: DataSource

    @Inject
    @Named("remoteDataSource")
    protected lateinit var remoteDataSource: DataSource

    abstract suspend fun getMovies(): Resource<List<Movie>>
    abstract suspend fun getTvShows(): Resource<List<TvShow>>
    abstract suspend fun getMovieById(id: Int): Resource<Movie>
    abstract suspend fun getTvShowById(id: Int): Resource<TvShow>
}