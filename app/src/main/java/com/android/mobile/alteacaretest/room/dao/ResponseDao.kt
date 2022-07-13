package com.android.mobile.alteacaretest.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.mobile.alteacaretest.model.ResponseData

@Dao
interface ResponseDao {
    @Query("Select * from responsedata")
    fun getResponseData() : ResponseData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDoctor(response: ResponseData)
}