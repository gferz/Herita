package com.example.herita.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    private val gson = Gson()

    // List<String> <-> JSON
    @TypeConverter
    @JvmStatic
    fun fromStringList(value: List<String>?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    @JvmStatic
    fun toStringList(value: String?): List<String>? {
        return value?.let {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson<List<String>>(it, type)
        }
    }

    // List<Int> <-> JSON
    @TypeConverter
    @JvmStatic
    fun fromIntList(value: List<Int>?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    @JvmStatic
    fun toIntList(value: String?): List<Int>? {
        return value?.let {
            val type = object : TypeToken<List<Int>>() {}.type
            gson.fromJson<List<Int>>(it, type)
        }
    }
}
