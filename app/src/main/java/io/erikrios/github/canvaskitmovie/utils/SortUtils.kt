package io.erikrios.github.canvaskitmovie.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import io.erikrios.github.canvaskitmovie.database.DatabaseContract

object SortUtils {

    enum class Sort { TITLE, RELEASE_DATE, POPULARITY, VOTE_AVERAGE, VOTE_COUNT, RANDOM }
    enum class CinemaType { MOVIE, TV_SHOW }

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
        }

        val sortQuery = StringBuilder().append("SELECT * FROM $tableName ")
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