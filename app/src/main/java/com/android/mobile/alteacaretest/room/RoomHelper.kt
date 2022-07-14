package com.android.mobile.alteacaretest.room

import com.android.mobile.alteacaretest.room.dao.DoctorCacheDao
import com.android.mobile.alteacaretest.room.dao.ResponseDao
import javax.inject.Inject

class RoomHelper @Inject constructor(val roomDb: RoomDb) {
    fun initDoctorDao() : ResponseDao? = roomDb.responseDao()
    fun initDoctorCacheDao() : DoctorCacheDao? = roomDb.doctorCacheDao()

}