package io.erikrios.github.canvaskitmovie.data.repository

import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.data.source.DataSource
import io.erikrios.github.canvaskitmovie.data.source.local.LocalDataSource
import io.erikrios.github.canvaskitmovie.utils.NetworkHelper
import io.erikrios.github.canvaskitmovie.utils.Resource
import io.erikrios.github.canvaskitmovie.utils.SortUtils
import javax.inject.Inject

class CinemaRepositoryImpl @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val localeDataSource: DataSource,
    private val remoteDataSource: DataSource
) : CinemaRepository {

    override suspend fun getMovies(): Resource<List<Movie>> {
        if (!networkHelper.isNetworkConnected()) {
            val cachedMovies = localeDataSource.getMovies()
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
            moviesResources.data?.let { (localeDataSource as LocalDataSource).addCaches(it) }
            return moviesResources
        }
    }

    override suspend fun getTvShows(): Resource<List<TvShow>> {
        if (!networkHelper.isNetworkConnected()) {
            val cachedTvShows = localeDataSource.getTvShows()
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
            tvShowsResources.data?.let { (localeDataSource as LocalDataSource).addCaches(it) }
            return tvShowsResources
        }
    }

    override suspend fun getMovieById(id: Int): Resource<Movie> {
        if (!networkHelper.isNetworkConnected()) {
            val cachedMovie = localeDataSource.getMovieDetails(id)
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
            val cachedTvShow = localeDataSource.getTvShowDetails(id)
            cachedTvShow.data?.let { return cachedTvShow } ?: return Resource.error(
                "Couldn't reach the server. Check your internet connection",
                null
            )
        } else {
            return remoteDataSource.getTvShowDetails(id)
        }
    }

    override suspend fun insertFavoriteMovie(movie: Movie): Long =
        (localeDataSource as LocalDataSource).insertFavoriteMovie(movie)

    override fun getFavoriteMovies(sort: SortUtils.Sort): androidx.paging.DataSource.Factory<Int, Movie> =
        (localeDataSource as LocalDataSource).getFavoriteMovies(sort)

    override suspend fun getFavoriteMovie(id: Int): Movie? =
        (localeDataSource as LocalDataSource).getFavoriteMovie(id)

    override suspend fun deleteFavoriteMovie(movie: Movie): Int =
        (localeDataSource as LocalDataSource).deleteFavoriteMovie(movie)

    override suspend fun insertFavoriteTvShow(tvShow: TvShow): Long =
        (localeDataSource as LocalDataSource).insertFavoriteTvShow(tvShow)

    override fun getFavoriteTvShows(sort: SortUtils.Sort): androidx.paging.DataSource.Factory<Int, TvShow> =
        (localeDataSource as LocalDataSource).getFavoriteTvShows(sort)

    override suspend fun getFavoriteTvShow(id: Int): TvShow? =
        (localeDataSource as LocalDataSource).getFavoriteTvShow(id)

    override suspend fun deleteFavoriteTvShow(tvShow: TvShow): Int =
        (localeDataSource as LocalDataSource).deleteFavoriteTvShow(tvShow)

    override suspend fun getTrending(): Resource<List<Movie>> {
        if (!networkHelper.isNetworkConnected()) {
            val cachedTrending = localeDataSource.getTrending()
            cachedTrending.data?.let {
                return if (it.isEmpty()) {
                    Resource.error(
                        "Couldn't reach the server. Check your internet connection",
                        null
                    )
                } else
                    cachedTrending
            } ?: return Resource.error(
                "Couldn't reach the server. Check your internet connection",
                null
            )
        } else {
            val trendingResource = remoteDataSource.getTrending()
            trendingResource.data?.let {
                (localeDataSource as LocalDataSource).addTrendingCaches(it)
            }
            return trendingResource
        }
    }
}