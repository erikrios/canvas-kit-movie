package io.erikrios.github.canvaskitmovie.data.source.locale

import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.data.source.DataSource
import io.erikrios.github.canvaskitmovie.utils.Resource

class LocaleDataSource : DataSource {

    private val movieCaches = mutableSetOf<Movie>()
    private val tvShowCaches = mutableSetOf<TvShow>()

    override suspend fun getMovies(): Resource<List<Movie>> {
        return Resource.success(movieCaches.toList())
    }

    override suspend fun getTvShows(): Resource<List<TvShow>> {
        return Resource.success(tvShowCaches.toList())
    }

    override suspend fun getMovieDetails(id: Int): Resource<Movie> {
        val movie = movieCaches.find { id == it.id }
        return Resource.success(movie)
    }

    override suspend fun getTvShowDetails(id: Int): Resource<TvShow> {
        val tvShow = tvShowCaches.find { id == it.id }
        return Resource.success(tvShow)
    }

    fun addMovieCaches(movies: List<Movie>) = movieCaches.addAll(movies)


    fun addTvShowCaches(tvShows: List<TvShow>) = tvShowCaches.addAll(tvShows)
}