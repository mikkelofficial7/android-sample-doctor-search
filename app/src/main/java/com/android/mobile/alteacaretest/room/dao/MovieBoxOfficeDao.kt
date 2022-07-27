package com.android.mobile.alteacaretest.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.mobile.alteacaretest.model.MovieBoxOfficeResponse

@Dao
interface MovieBoxOfficeDao {
    @Query("Select * from movieboxoffice")
    fun getAllBoxOfficeMovie() : MovieBoxOfficeResponse

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBoxOfficeMovie(movieBoxOfficeResponse: MovieBoxOfficeResponse)
}