package io.github.erikrios.canvaskitmovie.core.data.source.remote.network

import io.github.erikrios.canvaskitmovie.core.data.source.remote.response.ListResponse
import io.github.erikrios.canvaskitmovie.core.data.source.remote.response.movie.MovieResponse
import io.github.erikrios.canvaskitmovie.core.data.source.remote.response.trending.TrendingResponse
import io.github.erikrios.canvaskitmovie.core.data.source.remote.response.tvshow.TvShowResponse
import retrofit2.Response

interface TheMovieDatabaseApiHelper {
    suspend fun getMovies(): Response<ListResponse<MovieResponse>>
    suspend fun getTvShows(): Response<ListResponse<TvShowResponse>>
    suspend fun getMovieDetails(id: Int): Response<MovieResponse>
    suspend fun getTvShowDetails(id: Int): Response<TvShowResponse>
    suspend fun getTrending(): Response<ListResponse<TrendingResponse>>
    suspend fun getTrendingDetails(id: Int): Response<TrendingResponse>
}