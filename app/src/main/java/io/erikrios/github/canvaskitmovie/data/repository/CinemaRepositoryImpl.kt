package io.erikrios.github.canvaskitmovie.data.repository

import io.erikrios.github.canvaskitmovie.utils.DummyData.generateMovies
import io.erikrios.github.canvaskitmovie.utils.DummyData.generateTvShows
import javax.inject.Inject

class CinemaRepositoryImpl @Inject constructor() : CinemaRepository {

    override fun getMovies() = generateMovies()

    override fun getTvShows() = generateTvShows()
}