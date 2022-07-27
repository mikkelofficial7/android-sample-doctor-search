package com.android.mobile.alteacaretest.model.detail

import com.google.gson.annotations.SerializedName

data class Hospital(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("name")
    var name: String,
    var isChecked: Boolean = false,
)