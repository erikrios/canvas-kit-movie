package io.github.erikrios.canvaskitmovie.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import io.github.erikrios.canvaskitmovie.core.data.source.local.room.DatabaseContract

object SortUtils {

    enum class Sort { TITLE, RELEASE_DATE, POPULARITY, VOTE_AVERAGE, VOTE_COUNT, RANDOM }
    enum class CinemaType { MOVIE, TV_SHOW, TRENDING }

    fun getSortedQuery(sort: Sort, type: CinemaType): SimpleSQLiteQuery {
        val tableName: String
        val titleColumnName: String
        val releaseDateColumnName: String
        val popularityColumnName = "popularity"
        val voteAverageColumnName = "voteAverage"
        val voteCountColumnName = "voteCount"

        when (type) {
            CinemaType.MOVIE -> {
                tableName = DatabaseContract.MovieColumns.TABLE_NAME
                titleColumnName = "title"
                releaseDateColumnName = "releaseDate"
            }
            CinemaType.TV_SHOW -> {
                tableName = DatabaseContract.TvShowColumns.TABLE_NAME
                titleColumnName = "name"
                releaseDateColumnName = "firstAirDate"
            }
            CinemaType.TRENDING -> {
                tableName = DatabaseContract.TrendingColumns.TABLE_NAME
                titleColumnName = "title"
                releaseDateColumnName = "releaseDate"
            }
        }

        val sortQuery = StringBuilder().append("SELECT * FROM $tableName WHERE isFavorite = 1 ")
            .also {
                when (sort) {
                    Sort.TITLE -> it.append("ORDER BY $titleColumnName")
                    Sort.RELEASE_DATE -> it.append("ORDER BY $releaseDateColumnName")
                    Sort.POPULARITY -> it.append("ORDER BY $popularityColumnName")
                    Sort.VOTE_AVERAGE -> it.append("ORDER BY $voteAverageColumnName")
                    Sort.VOTE_COUNT -> it.append("ORDER BY $voteCountColumnName")
                    Sort.RANDOM -> it.append("ORDER BY RANDOM()")
                }
            }

        return SimpleSQLiteQuery(sortQuery.toString())
    }
}