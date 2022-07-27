package com.android.mobile.alteacaretest.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.android.mobile.alteacaretest.databinding.ItemDoctorListBinding
import com.android.mobile.alteacaretest.extension.toStringHtml
import com.android.mobile.alteacaretest.model.detail.Doctor
import com.bumptech.glide.Glide

class DoctorListViewHolder(private val binding: ItemDoctorListBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bindHolder(doctor: Doctor) {
        Glide.with(binding.root.context)
            .load(doctor.doctorImage)
            .into(binding.ivDoctor)

        binding.tvDoctorName.text = doctor.name
        binding.tvHospitalAndSpecialist.text = doctor.doctorHospitalSpecialist
        binding.tvDoctorAbout.text = doctor.about.toStringHtml()
        binding.tvDoctorPrice.text = doctor.price.textFormat
    }
}