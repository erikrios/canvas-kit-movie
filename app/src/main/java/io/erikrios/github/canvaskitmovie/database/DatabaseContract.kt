package io.erikrios.github.canvaskitmovie.database

import android.provider.BaseColumns

object DatabaseContract {

    const val DATABASE_NAME = "favorite_cinema_db.db"

    class MovieColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "movies"
            const val COLUMN_ID = BaseColumns._ID
        }
    }

    class TvShowColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "tv_show"
            const val COLUMN_ID = BaseColumns._ID
        }
    }
}