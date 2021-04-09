package io.erikrios.github.canvaskitmovie.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.erikrios.github.canvaskitmovie.data.model.Creator
import io.erikrios.github.canvaskitmovie.data.model.Genre

class Converters {

    @TypeConverter
    fun fromGenres(genres: List<Genre>): String {
        val gson = Gson()
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toGenres(json: String): List<Genre> {
        val listType = object : TypeToken<List<Genre>>() {}.type
        val gson = Gson()
        return gson.fromJson(json, listType)
    }

    @TypeConverter
    fun fromCreators(creators: List<Creator>): String {
        val gson = Gson()
        return gson.toJson(creators)
    }

    @TypeConverter
    fun toCreators(json: String): List<Creator> {
        val listType = object : TypeToken<List<Creator>>() {}.type
        val gson = Gson()
        return gson.fromJson(json, listType)
    }
}