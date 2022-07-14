package com.android.mobile.alteacaretest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DoctorLastSearch")
data class DoctorCache(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val doctorName: String
)
