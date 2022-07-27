package com.android.mobile.alteacaretest.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.mobile.alteacaretest.model.MovieComingSoonResponseData

@Dao
interface MovieComingSoonDao {
    @Query("Select * from moviecomingsoonresponse")
    fun getAllComingSoonMovie() : MovieComingSoonResponseData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllComingSoonMovie(movieComingSoonResponseData: MovieComingSoonResponseData)
}