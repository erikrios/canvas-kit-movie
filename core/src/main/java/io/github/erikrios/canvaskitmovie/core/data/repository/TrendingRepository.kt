package io.github.erikrios.canvaskitmovie.core.data.repository

import io.github.erikrios.canvaskitmovie.core.data.Resource
import io.github.erikrios.canvaskitmovie.core.data.source.local.LocalDataSource
import io.github.erikrios.canvaskitmovie.core.data.source.local.entity.GenreEntity
import io.github.erikrios.canvaskitmovie.core.data.source.local.entity.TrendingEntity
import io.github.erikrios.canvaskitmovie.core.data.source.remote.RemoteDataSource
import io.github.erikrios.canvaskitmovie.core.data.source.remote.network.TheMovieDatabaseApiResponse
import io.github.erikrios.canvaskitmovie.core.data.source.remote.response.trending.TrendingResponse
import io.github.erikrios.canvaskitmovie.core.domain.model.Trending
import io.github.erikrios.canvaskitmovie.core.domain.repository.ITrendingRepository
import io.github.erikrios.canvaskitmovie.core.utils.AppExecutors
import io.github.erikrios.canvaskitmovie.core.utils.DataMapper
import io.github.erikrios.canvaskitmovie.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TrendingRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITrendingRepository {

    override fun getTrendings(): Flow<Resource<List<Trending>>> =
        object :
            io.github.erikrios.canvaskitmovie.core.data.NetworkBoundResource<List<Trending>, List<TrendingResponse>>() {
            override fun loadFromDb(): Flow<List<Trending>> = localDataSource.getTrendings().map {
                DataMapper.TrendingDataMapper.mapEntitiesToDomain(it)
            }

            override fun shouldFetch(data: List<Trending>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<TheMovieDatabaseApiResponse<List<TrendingResponse>>> =
                remoteDataSource.getTrendings()

            override suspend fun saveCallResult(data: List<TrendingResponse>) {
                val trendings = DataMapper.TrendingDataMapper.mapResponsesToEntities(data)
                localDataSource.insertTrendings(trendings)
            }
        }.asFlow()

    override fun getTrending(id: Int): Flow<Resource<Trending>> =
        object :
            io.github.erikrios.canvaskitmovie.core.data.NetworkBoundResource<Trending, TrendingResponse>() {
            override fun loadFromDb(): Flow<Trending> = localDataSource.getTrending(id)
                .map { DataMapper.TrendingDataMapper.mapEntityToDomain(it as TrendingEntity) }

            override fun shouldFetch(data: Trending?): Boolean =
                data?.budget == null || data.genres == null || data.status == null || data.revenue == null

            override suspend fun createCall(): Flow<TheMovieDatabaseApiResponse<TrendingResponse>> =
                remoteDataSource.getTrendingDetails(id)

            override suspend fun saveCallResult(data: TrendingResponse) {
                val trendingEntity = localDataSource.getTrending(id).first() as TrendingEntity
                val trending = trendingEntity.copy(
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
                localDataSource.updateTrending(trending)
            }
        }.asFlow()

    override fun getFavoriteTrendings(sort: SortUtils.Sort): Flow<List<Trending>> =
        localDataSource.getFavoriteTrendings(sort).map {
            DataMapper.TrendingDataMapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteTrending(trending: Trending, state: Boolean) {
        val trendingEntity = DataMapper.TrendingDataMapper.mapDomainToEntity(trending)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTrending(trendingEntity, state)
        }
    }
}