package com.android.mobile.alteacaretest.room.converters

import androidx.room.TypeConverter
import com.android.mobile.alteacaretest.model.detail.MovieBoxOffice
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MovieBoxOfficeConverters {
    @TypeConverter
    @JvmStatic
    fun listToString(list: ArrayList<MovieBoxOffice>?) : String {
        return if(list.isNullOrEmpty())
            ""
        else
            Gson().toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun stringToList(value: String?) : ArrayList<MovieBoxOffice> {
        return if(value.isNullOrEmpty()) {
            arrayListOf()
        } else {
            val type = object : TypeToken<ArrayList<MovieBoxOffice>>(){}.type
            Gson().fromJson(value, type)
        }
    }
}