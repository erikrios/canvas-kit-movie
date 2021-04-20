package io.erikrios.github.canvaskitmovie.network

import io.erikrios.github.canvaskitmovie.data.model.DiscoverResponse
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDatabaseApiService {

    @GET("discover/movie")
    suspend fun getMovies(): Response<DiscoverResponse<Movie>>

    @GET("discover/tv")
    suspend fun getTvShows(): Response<DiscoverResponse<TvShow>>

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): Response<Movie>

    @GET("tv/{id}")
    suspend fun getTvShowDetails(@Path("id") id: Int): Response<TvShow>

    @GET("trending/movie/day")
    suspend fun getTrending(): Response<DiscoverResponse<Movie>>
}