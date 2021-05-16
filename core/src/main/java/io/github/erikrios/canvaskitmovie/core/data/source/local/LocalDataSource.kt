package io.github.erikrios.canvaskitmovie.core.data.source.local

import io.github.erikrios.canvaskitmovie.core.data.source.local.entity.MovieEntity
import io.github.erikrios.canvaskitmovie.core.data.source.local.entity.TrendingEntity
import io.github.erikrios.canvaskitmovie.core.data.source.local.entity.TvShowEntity
import io.github.erikrios.canvaskitmovie.core.data.source.local.room.MovieDao
import io.github.erikrios.canvaskitmovie.core.data.source.local.room.TrendingDao
import io.github.erikrios.canvaskitmovie.core.data.source.local.room.TvShowDao
import io.github.erikrios.canvaskitmovie.core.utils.SortUtils
import io.github.erikrios.canvaskitmovie.core.utils.SortUtils.getSortedQuery
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val movieDao: MovieDao,
    private val tvShowDao: TvShowDao,
    private val trendingDao: TrendingDao
) {

    fun getMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun getMovie(id: Int): Flow<MovieEntity?> = movieDao.getMovie(id)

    fun getFavoriteMovies(
        sort: SortUtils.Sort,
    ): Flow<List<MovieEntity>> =
        movieDao.getFavoriteMovies(getSortedQuery(sort, SortUtils.CinemaType.MOVIE))

    suspend fun updateMovie(movie: MovieEntity) = movieDao.updateMovie(movie)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.setFavoriteMovie(movie)
    }

    fun getTvShows(): Flow<List<TvShowEntity>> = tvShowDao.getTvShows()

    suspend fun insertTvShows(tvShows: List<TvShowEntity>) = tvShowDao.insertTvShows(tvShows)

    fun getTvShow(id: Int): Flow<TvShowEntity?> = tvShowDao.getTvShow(id)

    fun getFavoriteTvShows(
        sort: SortUtils.Sort,
    ): Flow<List<TvShowEntity>> =
        tvShowDao.getFavoriteTvShows(getSortedQuery(sort, SortUtils.CinemaType.TV_SHOW))

    suspend fun updateTvShow(tvShow: TvShowEntity) = tvShowDao.updateTvShow(tvShow)

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        tvShowDao.setFavoriteTvShow(tvShow)
    }

    fun getTrendings(): Flow<List<TrendingEntity>> = trendingDao.getTrendings()

    suspend fun insertTrendings(trendings: List<TrendingEntity>) =
        trendingDao.insertTrendings(trendings)

    fun getTrending(id: Int): Flow<TrendingEntity?> = trendingDao.getTrending(id)

    fun getFavoriteTrendings(
        sort: SortUtils.Sort
    ): Flow<List<TrendingEntity>> =
        trendingDao.getFavoriteTrendings(getSortedQuery(sort, SortUtils.CinemaType.TRENDING))

    suspend fun updateTrending(trending: TrendingEntity) = trendingDao.updateTrending(trending)

    fun setFavoriteTrending(trending: TrendingEntity, newState: Boolean) {
        trending.isFavorite = newState
        trendingDao.setFavoriteTrending(trending)
    }
}