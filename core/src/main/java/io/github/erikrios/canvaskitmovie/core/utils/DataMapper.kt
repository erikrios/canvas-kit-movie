package io.github.erikrios.canvaskitmovie.core.utils

import io.github.erikrios.canvaskitmovie.core.data.source.local.entity.*
import io.github.erikrios.canvaskitmovie.core.data.source.remote.response.movie.MovieResponse
import io.github.erikrios.canvaskitmovie.core.data.source.remote.response.trending.TrendingResponse
import io.github.erikrios.canvaskitmovie.core.data.source.remote.response.tvshow.TvShowResponse
import io.github.erikrios.canvaskitmovie.core.domain.model.*

object DataMapper {

    object MovieDataMapper {

        fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
            val movieEntities = mutableListOf<MovieEntity>()
            input.map {
                val movieEntity = MovieEntity(
                    id = it.id,
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    budget = it.budget,
                    genres = it.genres?.map { genre -> GenreEntity(genre.id, genre.name) },
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    title = it.title,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    revenue = it.revenue,
                    status = it.status,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    isFavorite = false
                )
                movieEntities.add(movieEntity)
            }
            return movieEntities
        }

        fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
            input.map {
                Movie(
                    id = it.id,
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    budget = it.budget,
                    genres = it.genres?.map { genreEntity ->
                        Genre(
                            genreEntity.id,
                            genreEntity.name
                        )
                    },
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    title = it.title,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    revenue = it.revenue,
                    status = it.status,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    isFavorite = it.isFavorite
                )
            }

        fun mapDomainToEntity(input: Movie) = MovieEntity(
            id = input.id,
            adult = input.adult,
            backdropPath = input.backdropPath,
            budget = input.budget,
            genres = input.genres?.map { genre -> GenreEntity(genre.id, genre.name) },
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            title = input.title,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            revenue = input.revenue,
            status = input.status,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite
        )

        fun mapEntityToDomain(input: MovieEntity) = Movie(
            id = input.id,
            adult = input.adult,
            backdropPath = input.backdropPath,
            budget = input.budget,
            genres = input.genres?.map { genreEntity -> Genre(genreEntity.id, genreEntity.name) },
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            title = input.title,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            revenue = input.revenue,
            status = input.status,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite
        )
    }

    object TvShowDataMapper {

        fun mapResponsesToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
            val tvShowEntities = mutableListOf<TvShowEntity>()
            input.map {
                val tvShowEntity = TvShowEntity(
                    id = it.id,
                    backdropPath = it.backdropPath,
                    genres = it.genres?.map { genre -> GenreEntity(genre.id, genre.name) },
                    originalLanguage = it.originalLanguage,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    status = it.status,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    isFavorite = false,
                    originalName = it.originalName,
                    name = it.name,
                    firstAirDate = it.firstAirDate,
                    creators = it.creators?.map { creatorResponse ->
                        CreatorEntity(
                            creatorResponse.id,
                            creatorResponse.name,
                            creatorResponse.profilePath
                        )
                    }
                )
                tvShowEntities.add(tvShowEntity)
            }
            return tvShowEntities
        }

        fun mapEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
            input.map {
                TvShow(
                    id = it.id,
                    backdropPath = it.backdropPath,
                    genres = it.genres?.map { genre -> Genre(genre.id, genre.name) },
                    originalLanguage = it.originalLanguage,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    status = it.status,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    isFavorite = it.isFavorite,
                    originalName = it.originalName,
                    name = it.name,
                    firstAirDate = it.firstAirDate,
                    creators = it.creators?.map { creator ->
                        Creator(
                            creator.id,
                            creator.name,
                            creator.profilePath
                        )
                    }
                )
            }

        fun mapDomainToEntity(input: TvShow) = TvShowEntity(
            id = input.id,
            backdropPath = input.backdropPath,
            genres = input.genres?.map { genre -> GenreEntity(genre.id, genre.name) },
            originalLanguage = input.originalLanguage,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            status = input.status,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite,
            originalName = input.originalName,
            name = input.name,
            firstAirDate = input.firstAirDate,
            creators = input.creators?.map { creatorEntity ->
                CreatorEntity(
                    creatorEntity.id,
                    creatorEntity.name,
                    creatorEntity.profilePath
                )
            }
        )

        fun mapEntityToDomain(input: TvShowEntity) = TvShow(
            id = input.id,
            backdropPath = input.backdropPath,
            genres = input.genres?.map { genre -> Genre(genre.id, genre.name) },
            originalLanguage = input.originalLanguage,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            status = input.status,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite,
            originalName = input.originalName,
            name = input.name,
            firstAirDate = input.firstAirDate,
            creators = input.creators?.map { creator ->
                Creator(
                    creator.id,
                    creator.name,
                    creator.profilePath
                )
            }
        )
    }

    object TrendingDataMapper {

        fun mapResponsesToEntities(input: List<TrendingResponse>): List<TrendingEntity> {
            val trendingEntities = mutableListOf<TrendingEntity>()
            input.map {
                val trendingEntity = TrendingEntity(
                    id = it.id,
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    budget = it.budget,
                    genres = it.genres?.map { genre -> GenreEntity(genre.id, genre.name) },
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    title = it.title,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    revenue = it.revenue,
                    status = it.status,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    isFavorite = false
                )
                trendingEntities.add(trendingEntity)
            }
            return trendingEntities
        }

        fun mapEntitiesToDomain(input: List<TrendingEntity>): List<Trending> =
            input.map {
                Trending(
                    id = it.id,
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    budget = it.budget,
                    genres = it.genres?.map { genreEntity ->
                        Genre(
                            genreEntity.id,
                            genreEntity.name
                        )
                    },
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    title = it.title,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    revenue = it.revenue,
                    status = it.status,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    isFavorite = it.isFavorite
                )
            }

        fun mapDomainToEntity(input: Trending) = TrendingEntity(
            id = input.id,
            adult = input.adult,
            backdropPath = input.backdropPath,
            budget = input.budget,
            genres = input.genres?.map { genre -> GenreEntity(genre.id, genre.name) },
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            title = input.title,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            revenue = input.revenue,
            status = input.status,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite
        )

        fun mapEntityToDomain(input: TrendingEntity) = Trending(
            id = input.id,
            adult = input.adult,
            backdropPath = input.backdropPath,
            budget = input.budget,
            genres = input.genres?.map { genreEntity -> Genre(genreEntity.id, genreEntity.name) },
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            title = input.title,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            revenue = input.revenue,
            status = input.status,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite
        )
    }
}