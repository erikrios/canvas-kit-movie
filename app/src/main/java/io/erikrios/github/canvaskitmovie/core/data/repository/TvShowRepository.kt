package io.erikrios.github.canvaskitmovie.core.data.repository

import io.erikrios.github.canvaskitmovie.core.data.NetworkBoundResource
import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.data.source.local.LocalDataSource
import io.erikrios.github.canvaskitmovie.core.data.source.local.entity.CreatorEntity
import io.erikrios.github.canvaskitmovie.core.data.source.local.entity.GenreEntity
import io.erikrios.github.canvaskitmovie.core.data.source.local.entity.TvShowEntity
import io.erikrios.github.canvaskitmovie.core.data.source.remote.RemoteDataSource
import io.erikrios.github.canvaskitmovie.core.data.source.remote.network.TheMovieDatabaseApiResponse
import io.erikrios.github.canvaskitmovie.core.data.source.remote.response.tvshow.TvShowResponse
import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow
import io.erikrios.github.canvaskitmovie.core.domain.repository.ITvShowRepository
import io.erikrios.github.canvaskitmovie.core.utils.AppExecutors
import io.erikrios.github.canvaskitmovie.core.utils.DataMapper
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TvShowRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITvShowRepository {

    override fun getTvShows(): Flow<Resource<List<TvShow>>> =
        object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDb(): Flow<List<TvShow>> = localDataSource.getTvShows().map {
                DataMapper.TvShowDataMapper.mapEntitiesToDomain(it)
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<TheMovieDatabaseApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShows()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShows = DataMapper.TvShowDataMapper.mapResponsesToEntities(data)
                localDataSource.insertTvShows(tvShows)
            }
        }.asFlow()

    override fun getTvShow(id: Int): Flow<Resource<TvShow>> =
        object : NetworkBoundResource<TvShow, TvShowResponse>() {
            override fun loadFromDb(): Flow<TvShow> = localDataSource.getTvShow(id)
                .map { DataMapper.TvShowDataMapper.mapEntityToDomain(it as TvShowEntity) }

            override fun shouldFetch(data: TvShow?): Boolean =
                data?.genres == null || data.status == null || data.creators == null

            override suspend fun createCall(): Flow<TheMovieDatabaseApiResponse<TvShowResponse>> =
                remoteDataSource.getTvShowDetails(id)

            override suspend fun saveCallResult(data: TvShowResponse) {
                val tvShowEntity = localDataSource.getTvShow(id).first() as TvShowEntity
                val tvShow = tvShowEntity.copy(
                    genres = data.genres?.map { genreResponse ->
                        GenreEntity(
                            genreResponse.id,
                            genreResponse.name
                        )
                    },
                    status = data.status,
                    creators = data.creators?.map { creatorResponse ->
                        CreatorEntity(
                            creatorResponse.id,
                            creatorResponse.name,
                            creatorResponse.profilePath
                        )
                    }
                )
                localDataSource.updateTvShow(tvShow)
            }
        }.asFlow()

    override fun getFavoriteTvShows(sort: SortUtils.Sort): Flow<List<TvShow>> =
        localDataSource.getFavoriteTvShows(sort).map {
            DataMapper.TvShowDataMapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) {
        val tvShowEntity = DataMapper.TvShowDataMapper.mapDomainToEntity(tvShow)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShowEntity, state)
        }
    }
}