package com.android.mobile.alteacaretest.room.typeconverter

import androidx.room.TypeConverter
import com.android.mobile.alteacaretest.model.detail.Doctor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DoctorListConverter {
    @TypeConverter
    @JvmStatic
    fun stringToList(value: String?) : ArrayList<Doctor> {
        return when {
            value.isNullOrEmpty() -> arrayListOf()
            else -> {
                val listType = object : TypeToken<ArrayList<Doctor>?>(){}.type
                Gson().fromJson(value, listType)
            }
        }
    }

    @TypeConverter
    @JvmStatic
    fun listToString(list: ArrayList<Doctor>) : String {
        return if(list.isEmpty()) "" else Gson().toJson(list)
    }
}