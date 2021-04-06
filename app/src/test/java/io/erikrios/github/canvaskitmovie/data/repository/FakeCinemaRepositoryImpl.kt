package io.erikrios.github.canvaskitmovie.data.repository

import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.data.source.DataSource
import io.erikrios.github.canvaskitmovie.data.source.local.LocalDataSource
import io.erikrios.github.canvaskitmovie.utils.NetworkHelper
import io.erikrios.github.canvaskitmovie.utils.Resource

class FakeCinemaRepositoryImpl(
    private val networkHelper: NetworkHelper,
    private val localDataSource: DataSource,
    private val remoteDataSource: DataSource
) : CinemaRepository {

    override suspend fun getMovies(): Resource<List<Movie>> {
        if (!networkHelper.isNetworkConnected()) {
            val cachedMovies = localDataSource.getMovies()
            cachedMovies.data?.let {
                return if (it.isEmpty())
                    Resource.error(
                        "Couldn't reach the server. Check your internet connection",
                        null
                    )
                else
                    cachedMovies
            } ?: return Resource.error(
                "Couldn't reach the server. Check your internet connection",
                null
            )
        } else {
            val moviesResources = remoteDataSource.getMovies()
            moviesResources.data?.let { (localDataSource as LocalDataSource).addCaches(it) }
            return moviesResources
        }
    }

    override suspend fun getTvShows(): Resource<List<TvShow>> {
        if (!networkHelper.isNetworkConnected()) {
            val cachedTvShows = localDataSource.getTvShows()
            cachedTvShows.data?.let {
                return if (it.isEmpty())
                    Resource.error(
                        "Couldn't reach the server. Check your internet connection",
                        null
                    )
                else
                    cachedTvShows
            } ?: return Resource.error(
                "Couldn't reach the server. Check your internet connection",
                null
            )
        } else {
            val tvShowsResources = remoteDataSource.getTvShows()
            tvShowsResources.data?.let { (localDataSource as LocalDataSource).addCaches(it) }
            return tvShowsResources
        }
    }

    override suspend fun getMovieById(id: Int): Resource<Movie> {
        if (!networkHelper.isNetworkConnected()) {
            val cachedMovie = localDataSource.getMovieDetails(id)
            cachedMovie.data?.let { return cachedMovie } ?: return Resource.error(
                "Couldn't reach the server. Check your internet connection",
                null
            )
        } else {
            return remoteDataSource.getMovieDetails(id)
        }
    }

    override suspend fun getTvShowById(id: Int): Resource<TvShow> {
        if (!networkHelper.isNetworkConnected()) {
            val cachedTvShow = localDataSource.getTvShowDetails(id)
            cachedTvShow.data?.let { return cachedTvShow } ?: return Resource.error(
                "Couldn't reach the server. Check your internet connection",
                null
            )
        } else {
            return remoteDataSource.getTvShowDetails(id)
        }
    }
}