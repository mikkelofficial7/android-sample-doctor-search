package com.android.mobile.alteacaretest.model

import androidx.room.*
import com.android.mobile.alteacaretest.model.detail.Doctor
import com.android.mobile.alteacaretest.room.typeconverter.DoctorListConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ResponseData")
data class ResponseData (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "status")
    @SerializedName("status")
    var status: String,
    @ColumnInfo(name = "message")
    @SerializedName("message")
    var message: String,
    @TypeConverters(DoctorListConverter::class)
    @SerializedName("data")
    var doctorList: ArrayList<Doctor>
)