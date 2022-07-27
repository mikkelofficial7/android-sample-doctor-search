package com.android.mobile.alteacaretest.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.mobile.alteacaretest.model.MoviePopularResponseData

@Dao
interface MoviePopularDao {
    @Query("Select * from moviepopularresponse")
    fun getAllPopularMovie() : MoviePopularResponseData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPopularMovie(moviePopularResponse: MoviePopularResponseData)
}