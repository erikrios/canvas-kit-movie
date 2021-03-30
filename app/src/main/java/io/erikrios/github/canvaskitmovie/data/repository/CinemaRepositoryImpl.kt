package io.erikrios.github.canvaskitmovie.data.repository

import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.utils.DummyData
import io.erikrios.github.canvaskitmovie.utils.DummyData.generateMovies
import io.erikrios.github.canvaskitmovie.utils.DummyData.generateTvShows
import javax.inject.Inject

class CinemaRepositoryImpl @Inject constructor() : CinemaRepository {

    override fun getMovies() = generateMovies()

    override fun getTvShows() = generateTvShows()

    override fun getMovieById(id: Int): Movie? = DummyData.getMovieById(id)

    override fun getTvShowById(id: Int): TvShow? = DummyData.getTvShowById(id)
}