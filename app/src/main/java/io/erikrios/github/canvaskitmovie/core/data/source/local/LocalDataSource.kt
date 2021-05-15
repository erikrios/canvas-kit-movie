package io.erikrios.github.canvaskitmovie.core.data.source.local

import io.erikrios.github.canvaskitmovie.core.data.source.local.entity.MovieEntity
import io.erikrios.github.canvaskitmovie.core.data.source.local.entity.TrendingEntity
import io.erikrios.github.canvaskitmovie.core.data.source.local.entity.TvShowEntity
import io.erikrios.github.canvaskitmovie.core.data.source.local.room.MovieDao
import io.erikrios.github.canvaskitmovie.core.data.source.local.room.TrendingDao
import io.erikrios.github.canvaskitmovie.core.data.source.local.room.TvShowDao
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils.getSortedQuery
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
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

    fun updateMovie(movie: MovieEntity) = movieDao.updateMovie(movie)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateMovie(movie)
    }

    fun getTvShows(): Flow<List<TvShowEntity>> = tvShowDao.getTvShows()

    suspend fun insertTvShows(tvShows: List<TvShowEntity>) = tvShowDao.insertTvShows(tvShows)

    suspend fun getTvShow(id: Int): Flow<TvShowEntity?> = tvShowDao.getTvShow(id)

    fun getFavoriteTvShows(
        sort: SortUtils.Sort,
    ): Flow<List<TvShowEntity>> =
        tvShowDao.getFavoriteTvShows(getSortedQuery(sort, SortUtils.CinemaType.TV_SHOW))

    fun updateTvShow(tvShow: TvShowEntity) = tvShowDao.updateTvShow(tvShow)

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        tvShowDao.updateTvShow(tvShow)
    }

    fun getTrendings(): Flow<List<TrendingEntity>> = trendingDao.getTrendings()

    suspend fun insertTrendings(trendings: List<TrendingEntity>) =
        trendingDao.insertTrendings(trendings)

    suspend fun getTrending(id: Int): Flow<TrendingEntity?> = trendingDao.getTrending(id)

    fun getFavoriteTrendings(
        sort: SortUtils.Sort
    ): Flow<List<TrendingEntity>> =
        trendingDao.getFavoriteTrendings(getSortedQuery(sort, SortUtils.CinemaType.TRENDING))

    fun updateTrending(trending: TrendingEntity) = trendingDao.updateTrending(trending)

    fun setFavoriteTrending(trending: TrendingEntity, newState: Boolean) {
        trending.isFavorite = newState
        trendingDao.updateTrending(trending)
    }
}