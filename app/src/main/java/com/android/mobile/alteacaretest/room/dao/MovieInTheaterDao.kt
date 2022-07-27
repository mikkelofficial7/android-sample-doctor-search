package com.android.mobile.alteacaretest.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.mobile.alteacaretest.model.MovieInTheaterResponseData
import com.android.mobile.alteacaretest.model.MoviePopularResponseData
import com.android.mobile.alteacaretest.model.MovieTop250ResponseData

@Dao
interface MovieInTheaterDao {
    @Query("Select * from movieintheaterresponse")
    fun getAllInTheaterMovie() : MovieInTheaterResponseData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllInTheaterMovie(movieInTheaterResponseData: MovieInTheaterResponseData)
}