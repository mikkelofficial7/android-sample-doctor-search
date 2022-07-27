package com.android.mobile.alteacaretest.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.mobile.alteacaretest.model.MovieTop250ResponseData

@Dao
interface MovieTop250Dao {
    @Query("Select * from movietop250response")
    fun getAllTop250Movie() : MovieTop250ResponseData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTop250Movie(movieTopResponse: MovieTop250ResponseData)
}