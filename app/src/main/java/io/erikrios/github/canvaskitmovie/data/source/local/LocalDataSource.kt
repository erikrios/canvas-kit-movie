package io.erikrios.github.canvaskitmovie.data.source.local

import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.data.source.DataSource
import io.erikrios.github.canvaskitmovie.database.FavoriteMovieDao
import io.erikrios.github.canvaskitmovie.database.FavoriteTvShowDao
import io.erikrios.github.canvaskitmovie.utils.Resource
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao,
    private val favoriteTvShowDao: FavoriteTvShowDao
) : DataSource {

    private val movieCaches = mutableSetOf<Movie>()
    private val tvShowCaches = mutableSetOf<TvShow>()
    private val trendingCaches = mutableSetOf<Movie>()

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

    suspend fun insertFavoriteMovie(movie: Movie) = favoriteMovieDao.insert(movie)

    suspend fun getFavoriteMovies() = favoriteMovieDao.getMovies()

    suspend fun getFavoriteMovie(id: Int) = favoriteMovieDao.getMovie(id)

    suspend fun deleteFavoriteMovie(movie: Movie) = favoriteMovieDao.deleteMovie(movie)

    suspend fun insertFavoriteTvShow(tvShow: TvShow) = favoriteTvShowDao.insert(tvShow)

    suspend fun getFavoriteTvShows() = favoriteTvShowDao.getTvShows()

    suspend fun getFavoriteTvShow(id: Int) = favoriteTvShowDao.getTvShow(id)

    suspend fun deleteFavoriteTvShow(tvShow: TvShow) = favoriteTvShowDao.deleteTvShow(tvShow)

    override suspend fun getTrending(): Resource<List<Movie>> {
        return Resource.success(trendingCaches.toList())
    }

    @JvmName("addMovieCaches")
    fun addCaches(movies: List<Movie>) = movieCaches.addAll(movies)

    @JvmName("addTvShowCaches")
    fun addCaches(tvShows: List<TvShow>) = tvShowCaches.addAll(tvShows)

    fun addTrendingCaches(movies: List<Movie>) = trendingCaches.addAll(movies)
}