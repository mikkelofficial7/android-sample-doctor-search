package com.android.mobile.alteacaretest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.android.mobile.alteacaretest.model.detail.MovieBoxOffice
import com.android.mobile.alteacaretest.room.converters.MovieBoxOfficeConverters

@Entity(tableName = "MovieBoxOffice")
class MovieBoxOfficeResponse(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 1,

    @TypeConverters(MovieBoxOfficeConverters::class)
    @ColumnInfo(name = "listInTheaterMovie")
    var items: ArrayList<MovieBoxOffice>,

    @ColumnInfo(name = "errorMessage")
    var errorMessage: String,
)