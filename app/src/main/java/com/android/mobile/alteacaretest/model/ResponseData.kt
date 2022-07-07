package com.android.mobile.alteacaretest.model

import com.android.mobile.alteacaretest.model.detail.Doctor
import com.google.gson.annotations.SerializedName

data class ResponseData (
    @SerializedName("status")
    var status: String,
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    var doctorList: ArrayList<Doctor>
)