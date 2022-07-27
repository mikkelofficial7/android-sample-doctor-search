package com.android.mobile.alteacaretest.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.mobile.alteacaretest.model.DoctorCache
import com.android.mobile.alteacaretest.model.ResponseData
import com.android.mobile.alteacaretest.room.dao.DoctorCacheDao
import com.android.mobile.alteacaretest.room.dao.ResponseDao
import com.android.mobile.alteacaretest.room.typeconverter.DoctorListConverter

@Database(entities = [ResponseData::class, DoctorCache::class], version = 1, exportSchema = false)

@TypeConverters(DoctorListConverter::class)

abstract class RoomDb : RoomDatabase() {
    abstract fun responseDao() : ResponseDao?
    abstract fun doctorCacheDao() : DoctorCacheDao?
}