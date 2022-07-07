package com.android.mobile.alteacaretest.model.detail

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("formats")
    var format: Format
)

data class Format(
    @SerializedName("thumbnail")
    var thumbnail: String,
    @SerializedName("medium")
    var medium: String,
    @SerializedName("small")
    var small: String
)