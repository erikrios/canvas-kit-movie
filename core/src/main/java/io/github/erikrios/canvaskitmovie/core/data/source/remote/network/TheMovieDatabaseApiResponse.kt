package io.github.erikrios.canvaskitmovie.core.data.source.remote.network

sealed class TheMovieDatabaseApiResponse<out R> {
    data class Success<out T>(val data: T) : TheMovieDatabaseApiResponse<T>()
    data class Error(val errorMessage: String) : TheMovieDatabaseApiResponse<Nothing>()
    object Empty : TheMovieDatabaseApiResponse<Nothing>()
}