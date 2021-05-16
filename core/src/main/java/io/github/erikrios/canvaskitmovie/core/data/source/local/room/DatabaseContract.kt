package io.github.erikrios.canvaskitmovie.core.data.source.local.room

import android.provider.BaseColumns

object DatabaseContract {

    const val DATABASE_NAME = "cinema_db.db"

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

    class TrendingColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "trending"
            const val COLUMN_ID = BaseColumns._ID
        }
    }
}