package com.android.mobile.alteacaretest.model.detail

import com.google.gson.annotations.SerializedName

data class Doctor (
    @SerializedName("name")
    var name: String,
    @SerializedName("about_preview")
    var about: String,
    @SerializedName("photo")
    var photo: Photo,
    @SerializedName("hospital")
    var hospitalList: List<Hospital>,
    @SerializedName("specialization")
    var specialization: Specialization,
    @SerializedName("price")
    var price: Price,
) {
    val hospital: Hospital
        get() {
            return hospitalList[0]
        }

    val doctorImage: String
        get() {
            return photo.format.thumbnail
        }

    val doctorHospitalSpecialist: String
        get() {
            return "${hospital.name} (${specialization.name})"
        }
}