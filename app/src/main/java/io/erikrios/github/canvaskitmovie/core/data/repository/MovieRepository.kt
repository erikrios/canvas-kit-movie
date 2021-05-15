package io.erikrios.github.canvaskitmovie.core.data.repository

import io.erikrios.github.canvaskitmovie.core.data.NetworkBoundResource
import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.data.source.local.LocalDataSource
import io.erikrios.github.canvaskitmovie.core.data.source.local.entity.GenreEntity
import io.erikrios.github.canvaskitmovie.core.data.source.local.entity.MovieEntity
import io.erikrios.github.canvaskitmovie.core.data.source.remote.RemoteDataSource
import io.erikrios.github.canvaskitmovie.core.data.source.remote.network.TheMovieDatabaseApiResponse
import io.erikrios.github.canvaskitmovie.core.data.source.remote.response.movie.MovieResponse
import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.core.domain.repository.IMovieRepository
import io.erikrios.github.canvaskitmovie.core.utils.AppExecutors
import io.erikrios.github.canvaskitmovie.core.utils.DataMapper
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDb(): Flow<List<Movie>> = localDataSource.getMovies().map {
                DataMapper.MovieDataMapper.mapEntitiesToDomain(it)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<TheMovieDatabaseApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movies = DataMapper.MovieDataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movies)
            }
        }.asFlow()

    override fun getMovie(id: Int): Flow<Resource<Movie>> =
        object : NetworkBoundResource<Movie, MovieResponse>() {
            override fun loadFromDb(): Flow<Movie> = localDataSource.getMovie(id)
                .map { DataMapper.MovieDataMapper.mapEntityToDomain(it as MovieEntity) }

            override fun shouldFetch(data: Movie?): Boolean =
                data?.budget == null || data.genres == null || data.status == null || data.revenue == null

            override suspend fun createCall(): Flow<TheMovieDatabaseApiResponse<MovieResponse>> =
                remoteDataSource.getMovieDetails(id)

            override suspend fun saveCallResult(data: MovieResponse) {
                val movieEntity = localDataSource.getMovie(id).first() as MovieEntity
                val movie = movieEntity.copy(
                    budget = data.budget,
                    genres = data.genres?.map { genreResponse ->
                        GenreEntity(
                            genreResponse.id,
                            genreResponse.name
                        )
                    },
                    status = data.status,
                    revenue = data.revenue
                )
                localDataSource.updateMovie(movie)
            }
        }.asFlow()

    override fun getFavoriteMovies(sort: SortUtils.Sort): Flow<List<Movie>> =
        localDataSource.getFavoriteMovies(sort).map {
            DataMapper.MovieDataMapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.MovieDataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }
}