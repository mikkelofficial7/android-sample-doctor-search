package com.android.mobile.alteacaretest.room.converters

import androidx.room.TypeConverter
import com.android.mobile.alteacaretest.model.detail.MovieList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MovieListConverters {
    @TypeConverter
    @JvmStatic
    fun listToString(list: ArrayList<MovieList>?) : String {
        return if(list.isNullOrEmpty())
            ""
        else
            Gson().toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun stringToList(value: String?) : ArrayList<MovieList> {
        return if(value.isNullOrEmpty()) {
            arrayListOf()
        } else {
            val type = object : TypeToken<ArrayList<MovieList>>(){}.type
            Gson().fromJson(value, type)
        }
    }
}