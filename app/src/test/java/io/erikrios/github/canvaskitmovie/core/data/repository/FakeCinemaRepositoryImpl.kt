package io.erikrios.github.canvaskitmovie.core.data.repository

import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow
import io.erikrios.github.canvaskitmovie.core.data.source.local.LocalDataSource
import io.erikrios.github.canvaskitmovie.core.domain.repository.ITrendingRepository
import io.erikrios.github.canvaskitmovie.core.utils.NetworkHelper
import io.github.erikrios.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils

class FakeCinemaRepositoryImpl(
    private val networkHelper: NetworkHelper,
    private val localDataSource: DataSource,
    private val remoteDataSource: DataSource
) : ITrendingRepository {

    override suspend fun getMovies(): io.github.erikrios.canvaskitmovie.core.data.Resource<List<Movie>> {
        if (!networkHelper.isNetworkConnected()) {
            val cachedMovies = localDataSource.getMovies()
            cachedMovies.data?.let {
                return if (it.isEmpty())
                    io.github.erikrios.canvaskitmovie.core.data.Resource.error(
                        "Couldn't reach the server. Check your internet connection",
                        null
                    )
                else
                    cachedMovies
            } ?: return io.github.erikrios.canvaskitmovie.core.data.Resource.error(
                "Couldn't reach the server. Check your internet connection",
                null
            )
        } else {
            val moviesResources = remoteDataSource.getMovies()
            moviesResources.data?.let { (localDataSource as LocalDataSource).addCaches(it) }
            return moviesResources
        }
    }

    override suspend fun getTvShows(): io.github.erikrios.canvaskitmovie.core.data.Resource<List<TvShow>> {
        if (!networkHelper.isNetworkConnected()) {
            val cachedTvShows = localDataSource.getTvShows()
            cachedTvShows.data?.let {
                return if (it.isEmpty())
                    io.github.erikrios.canvaskitmovie.core.data.Resource.error(
                        "Couldn't reach the server. Check your internet connection",
                        null
                    )
                else
                    cachedTvShows
            } ?: return io.github.erikrios.canvaskitmovie.core.data.Resource.error(
                "Couldn't reach the server. Check your internet connection",
                null
            )
        } else {
            val tvShowsResources = remoteDataSource.getTvShows()
            tvShowsResources.data?.let { (localDataSource as LocalDataSource).addCaches(it) }
            return tvShowsResources
        }
    }

    override suspend fun getMovieById(id: Int): io.github.erikrios.canvaskitmovie.core.data.Resource<Movie> {
        if (!networkHelper.isNetworkConnected()) {
            val cachedMovie = localDataSource.getMovieDetails(id)
            cachedMovie.data?.let { return cachedMovie } ?: return io.github.erikrios.canvaskitmovie.core.data.Resource.error(
                "Couldn't reach the server. Check your internet connection",
                null
            )
        } else {
            return remoteDataSource.getMovieDetails(id)
        }
    }

    override suspend fun getTvShowById(id: Int): io.github.erikrios.canvaskitmovie.core.data.Resource<TvShow> {
        if (!networkHelper.isNetworkConnected()) {
            val cachedTvShow = localDataSource.getTvShowDetails(id)
            cachedTvShow.data?.let { return cachedTvShow } ?: return io.github.erikrios.canvaskitmovie.core.data.Resource.error(
                "Couldn't reach the server. Check your internet connection",
                null
            )
        } else {
            return remoteDataSource.getTvShowDetails(id)
        }
    }

    override suspend fun insertFavoriteMovie(movie: Movie): Long =
        (localDataSource as LocalDataSource).insertFavoriteMovie(movie)

    override fun getFavoriteMovies(sort: SortUtils.Sort): androidx.paging.DataSource.Factory<Int, Movie> =
        (localDataSource as LocalDataSource).getFavoriteMovies(sort)

    override suspend fun getFavoriteMovie(id: Int): Movie? =
        (localDataSource as LocalDataSource).getFavoriteMovie(id)

    override suspend fun deleteFavoriteMovie(movie: Movie): Int =
        (localDataSource as LocalDataSource).deleteFavoriteMovie(movie)

    override suspend fun insertFavoriteTvShow(tvShow: TvShow): Long =
        (localDataSource as LocalDataSource).insertFavoriteTvShow(tvShow)

    override fun getFavoriteTvShows(sort: SortUtils.Sort): androidx.paging.DataSource.Factory<Int, TvShow> =
        (localDataSource as LocalDataSource).getFavoriteTvShows(sort)

    override suspend fun getFavoriteTvShow(id: Int): TvShow? =
        (localDataSource as LocalDataSource).getFavoriteTvShow(id)

    override suspend fun deleteFavoriteTvShow(tvShow: TvShow): Int =
        (localDataSource as LocalDataSource).deleteFavoriteTvShow(tvShow)

    override suspend fun getTrending(): io.github.erikrios.canvaskitmovie.core.data.Resource<List<Movie>> {
        if (!networkHelper.isNetworkConnected()) {
            val cachedTrending = localDataSource.getTrending()
            cachedTrending.data?.let {
                return if (it.isEmpty()) {
                    io.github.erikrios.canvaskitmovie.core.data.Resource.error(
                        "Couldn't reach the server. Check your internet connection",
                        null
                    )
                } else
                    cachedTrending
            } ?: return io.github.erikrios.canvaskitmovie.core.data.Resource.error(
                "Couldn't reach the server. Check your internet connection",
                null
            )
        } else {
            val trendingResource = remoteDataSource.getTrending()
            trendingResource.data?.let {
                (localDataSource as LocalDataSource).addTrendingCaches(it)
            }
            return trendingResource
        }
    }
}