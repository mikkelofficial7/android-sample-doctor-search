package com.android.mobile.alteacaretest.model.detail

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("raw")
    var numberFormat: Long,
    @SerializedName("formatted")
    var textFormat: String
)