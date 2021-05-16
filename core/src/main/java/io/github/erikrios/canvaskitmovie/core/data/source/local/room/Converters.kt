package io.github.erikrios.canvaskitmovie.core.data.source.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.erikrios.canvaskitmovie.core.data.source.local.entity.CreatorEntity
import io.github.erikrios.canvaskitmovie.core.data.source.local.entity.GenreEntity

class Converters {

    @TypeConverter
    fun fromGenres(genres: List<GenreEntity>?): String {
        val gson = Gson()
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toGenres(json: String): List<GenreEntity>? {
        val listType = object : TypeToken<List<GenreEntity>?>() {}.type
        val gson = Gson()
        return gson.fromJson(json, listType)
    }

    @TypeConverter
    fun fromCreators(creators: List<CreatorEntity>?): String {
        val gson = Gson()
        return gson.toJson(creators)
    }

    @TypeConverter
    fun toCreators(json: String): List<CreatorEntity>? {
        val listType = object : TypeToken<List<CreatorEntity>?>() {}.type
        val gson = Gson()
        return gson.fromJson(json, listType)
    }
}