package com.android.mobile.alteacaretest.model.detail

import com.google.gson.annotations.SerializedName

data class Specialization(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("name")
    var name: String,
    var isChecked: Boolean = false
)