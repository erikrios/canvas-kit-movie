package io.erikrios.github.canvaskitmovie.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?) = Resource(Status.SUCCESS, data, null)


        fun <T> error(message: String?) = Resource(Status.ERROR, null, message)


        fun <T> loading(): Resource<T> = Resource(Status.LOADING, null, null)
    }
}