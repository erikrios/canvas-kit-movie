package io.erikrios.github.canvaskitmovie.core.data.source.remote

import io.erikrios.github.canvaskitmovie.core.data.source.remote.network.TheMovieDatabaseApiHelper
import io.erikrios.github.canvaskitmovie.core.data.source.remote.network.TheMovieDatabaseApiResponse
import io.erikrios.github.canvaskitmovie.core.data.source.remote.response.ListResponse
import io.erikrios.github.canvaskitmovie.core.data.source.remote.response.movie.MovieResponse
import io.erikrios.github.canvaskitmovie.core.data.source.remote.response.trending.TrendingResponse
import io.erikrios.github.canvaskitmovie.core.data.source.remote.response.tvshow.TvShowResponse
import io.erikrios.github.canvaskitmovie.core.utils.EspressoIdlingResource.decrement
import io.erikrios.github.canvaskitmovie.core.utils.EspressoIdlingResource.increment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiHelper: TheMovieDatabaseApiHelper) {

    suspend fun getMovies(): Flow<TheMovieDatabaseApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                increment()
                val response = apiHelper.getMovies()
                if (response.isSuccessful) {
                    decrement()
                    emit(TheMovieDatabaseApiResponse.Success((response.body() as ListResponse).results))
                } else {
                    emit(TheMovieDatabaseApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(TheMovieDatabaseApiResponse.Error("Couldn't reach the server. Check your internet connection"))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShows(): Flow<TheMovieDatabaseApiResponse<List<TvShowResponse>>> {
        return flow {
            try {
                increment()
                val response = apiHelper.getTvShows()
                if (response.isSuccessful) {
                    decrement()
                    emit(TheMovieDatabaseApiResponse.Success((response.body() as ListResponse).results))
                } else {
                    decrement()
                    emit(TheMovieDatabaseApiResponse.Empty)
                }
            } catch (e: Exception) {
                decrement()
                emit(TheMovieDatabaseApiResponse.Error("Couldn't reach the server. Check your internet connec)tion"))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieDetails(id: Int): Flow<TheMovieDatabaseApiResponse<MovieResponse>> {
        return flow {
            try {
                increment()
                val response = apiHelper.getMovieDetails(id)
                if (response.isSuccessful) {
                    decrement()
                    emit(TheMovieDatabaseApiResponse.Success(response.body() as MovieResponse))
                } else {
                    decrement()
                    emit(TheMovieDatabaseApiResponse.Empty)
                }
            } catch (e: Exception) {
                decrement()
                emit(TheMovieDatabaseApiResponse.Error("Couldn't reach the server. Check your i)nternet connection"))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShowDetails(id: Int): Flow<TheMovieDatabaseApiResponse<TvShowResponse>> {
        return flow {
            try {
                increment()
                val response = apiHelper.getTvShowDetails(id)
                if (response.isSuccessful) {
                    decrement()
                    emit(TheMovieDatabaseApiResponse.Success(response.body() as TvShowResponse))
                } else {
                    decrement()
                    emit(TheMovieDatabaseApiResponse.Empty)
                }
            } catch (e: Exception) {
                decrement()
                emit(TheMovieDatabaseApiResponse.Error("Couldn't reach the server. Check your internet) connection"))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTrendings(): Flow<TheMovieDatabaseApiResponse<List<TrendingResponse>>> {
        return flow {
            try {
                increment()
                val response = apiHelper.getTrending()
                if (response.isSuccessful) {
                    decrement()
                    emit(TheMovieDatabaseApiResponse.Success((response.body() as ListResponse).results))
                } else {
                    decrement()
                    emit(TheMovieDatabaseApiResponse.Empty)
                }
            } catch (e: Exception) {
                decrement()
                emit(TheMovieDatabaseApiResponse.Error("Couldn't reach the server. Check your intern)et connection"))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTrendingDetails(id: Int): Flow<TheMovieDatabaseApiResponse<TrendingResponse>> {
        return flow {
            try {
                increment()
                val response = apiHelper.getTrendingDetails(id)
                if (response.isSuccessful) {
                    decrement()
                    emit(TheMovieDatabaseApiResponse.Success(response.body() as TrendingResponse))
                } else {
                    decrement()
                    emit(TheMovieDatabaseApiResponse.Empty)
                }
            } catch (e: Exception) {
                decrement()
                emit(TheMovieDatabaseApiResponse.Error("Couldn't reach the server. Check your internet connection"))
            }
        }.flowOn(Dispatchers.IO)
    }
}