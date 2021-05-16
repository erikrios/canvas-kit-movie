package io.erikrios.github.canvaskitmovie.core.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val countingIdlingResource = CountingIdlingResource(RESOURCE, true)

    val idlingResource: IdlingResource
        get() = countingIdlingResource

    fun increment() = countingIdlingResource.increment()

    fun decrement() = countingIdlingResource.decrement()
}