package io.github.erikrios.canvaskitmovie.core.data

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : io.github.erikrios.canvaskitmovie.core.data.Resource<T>(data)
    class Loading<T>(data: T? = null) : io.github.erikrios.canvaskitmovie.core.data.Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : io.github.erikrios.canvaskitmovie.core.data.Resource<T>(data, message)
}