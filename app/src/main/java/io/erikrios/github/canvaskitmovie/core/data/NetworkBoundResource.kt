package io.erikrios.github.canvaskitmovie.core.data

import io.erikrios.github.canvaskitmovie.core.data.source.remote.network.TheMovieDatabaseApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestsType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDb().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is TheMovieDatabaseApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDb().map { Resource.Success(it) })
                }
                is TheMovieDatabaseApiResponse.Empty -> {
                    emitAll(loadFromDb().map { Resource.Success(it) })
                }
                is TheMovieDatabaseApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDb().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDb(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<TheMovieDatabaseApiResponse<RequestsType>>

    protected abstract suspend fun saveCallResult(data: RequestsType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}