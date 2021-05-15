package io.erikrios.github.canvaskitmovie.core.data.source.remote.network

import io.erikrios.github.canvaskitmovie.core.data.source.remote.response.ListResponse
import io.erikrios.github.canvaskitmovie.core.data.source.remote.response.movie.MovieResponse
import io.erikrios.github.canvaskitmovie.core.data.source.remote.response.trending.TrendingResponse
import io.erikrios.github.canvaskitmovie.core.data.source.remote.response.tvshow.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDatabaseApiService {

    @GET("discover/movie")
    suspend fun getMovies(): Response<ListResponse<MovieResponse>>

    @GET("discover/tv")
    suspend fun getTvShows(): Response<ListResponse<TvShowResponse>>

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): Response<MovieResponse>

    @GET("tv/{id}")
    suspend fun getTvShowDetails(@Path("id") id: Int): Response<TvShowResponse>

    @GET("trending/movie/day")
    suspend fun getTrending(): Response<ListResponse<TrendingResponse>>

    @GET("movie/{id}")
    suspend fun getTrendingDetails(@Path("id") id: Int): Response<TrendingResponse>
}