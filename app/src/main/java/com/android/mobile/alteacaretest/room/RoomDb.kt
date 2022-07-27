package com.android.mobile.alteacaretest.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.mobile.alteacaretest.model.*
import com.android.mobile.alteacaretest.room.converters.MovieBoxOfficeConverters
import com.android.mobile.alteacaretest.room.converters.MovieListConverters
import com.android.mobile.alteacaretest.room.dao.*

@Database(entities = [
    MoviePopularResponseData::class,
    MovieTop250ResponseData::class,
    MovieInTheaterResponseData::class,
    MovieComingSoonResponseData::class,
    MovieBoxOfficeResponse::class
], version = 1, exportSchema = false)

@TypeConverters(
    MovieListConverters::class,
    MovieBoxOfficeConverters::class
)

abstract class RoomDb : RoomDatabase() {
    abstract fun moviePopularDao() : MoviePopularDao?
    abstract fun movieTop250Dao() : MovieTop250Dao?
    abstract fun movieInTheaterDao() : MovieInTheaterDao?
    abstract fun movieComingSoonDao() : MovieComingSoonDao?
    abstract fun movieBoxOfficeDao() : MovieBoxOfficeDao?
}