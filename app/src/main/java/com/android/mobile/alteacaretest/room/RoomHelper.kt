package com.android.mobile.alteacaretest.room

import com.android.mobile.alteacaretest.room.dao.*
import javax.inject.Inject

class RoomHelper @Inject constructor(val roomDb: RoomDb) {
    fun initMoviePopularDao() : MoviePopularDao? = roomDb.moviePopularDao()
    fun initMovieTop250Dao() : MovieTop250Dao? = roomDb.movieTop250Dao()
    fun initMovieInTheaterDao() : MovieInTheaterDao? = roomDb.movieInTheaterDao()
    fun initMovieComingSoonDao() : MovieComingSoonDao? = roomDb.movieComingSoonDao()
    fun initMovieBoxOfficeDao() : MovieBoxOfficeDao? = roomDb.movieBoxOfficeDao()
}