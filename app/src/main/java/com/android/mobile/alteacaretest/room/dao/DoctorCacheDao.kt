package com.android.mobile.alteacaretest.room.dao

import androidx.room.*
import com.android.mobile.alteacaretest.model.DoctorCache

@Dao
interface DoctorCacheDao {
    @Query("Select * from doctorlastsearch")
    fun getAllDoctorCache() : List<DoctorCache>

    @Query("Select * from doctorlastsearch ORDER BY id limit 1")
    fun getFirstRowDoctorCache() : DoctorCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDoctorCache(doctorCache: DoctorCache)

    @Delete
    fun deleteDoctorCache(doctorCache: DoctorCache)
}