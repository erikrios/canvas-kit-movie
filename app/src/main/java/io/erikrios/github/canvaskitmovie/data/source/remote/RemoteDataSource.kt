package io.erikrios.github.canvaskitmovie.data.source.remote

import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.data.source.DataSource
import io.erikrios.github.canvaskitmovie.network.TheMovieDatabaseApiHelper
import io.erikrios.github.canvaskitmovie.utils.Resource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiHelper: TheMovieDatabaseApiHelper) :
    DataSource {
    override suspend fun getMovies(): Resource<List<Movie>> {
        return try {
            val response = apiHelper.getMovies()
            if (response.isSuccessful)
                Resource.success(response.body()?.results)
            else
                Resource.error("Internal server error.", null)
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override suspend fun getTvShows(): Resource<List<TvShow>> {
        return try {
            val response = apiHelper.getTvShows()
            if (response.isSuccessful)
                Resource.success(response.body()?.results)
            else
                Resource.error("Internal server error.", null)
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override suspend fun getMovieDetails(id: Int): Resource<Movie> {
        return try {
            val response = apiHelper.getMovieDetails(id)
            if (response.isSuccessful)
                Resource.success(response.body())
            else Resource.error("Internal server error.", null)
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override suspend fun getTvShowDetails(id: Int): Resource<TvShow> {
        return try {
            val response = apiHelper.getTvShowDetails(id)
            if (response.isSuccessful)
                Resource.success(response.body())
            else Resource.error("Internal server error.", null)
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}