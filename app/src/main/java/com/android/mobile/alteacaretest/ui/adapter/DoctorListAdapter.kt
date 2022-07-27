package com.android.mobile.alteacaretest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mobile.alteacaretest.databinding.ItemDoctorListBinding
import com.android.mobile.alteacaretest.model.detail.Doctor
import com.android.mobile.alteacaretest.model.detail.Hospital
import com.android.mobile.alteacaretest.model.detail.Specialization
import com.android.mobile.alteacaretest.ui.viewholder.DoctorListViewHolder
import java.util.*
import kotlin.collections.ArrayList

class DoctorListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private var listDoctor: ArrayList<Doctor> = ArrayList()
    private var listDoctorFiltered: ArrayList<Doctor> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = ItemDoctorListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoctorListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DoctorListViewHolder).bindHolder(listDoctorFiltered[position])
    }

    override fun getItemCount(): Int {
        return listDoctorFiltered.size
    }

    fun addData(list: ArrayList<Doctor>) {
        listDoctor = list
        listDoctorFiltered = listDoctor
        notifyDataSetChanged()
    }

    fun setFilterDoctor(listHospital: List<Hospital>,
                        listSpecialize: List<Specialization>,
                        doctorName: String) {

        val listDoctorSearchFound = if(doctorName.isEmpty()) {
            listDoctor
        } else {
            listDoctor.filter {
                it.name.lowercase(Locale.getDefault()).contains(doctorName)
            } as ArrayList<Doctor>
        }

        val doctorFilteredHospital = runHospitalFilter(listDoctorSearchFound, listHospital)
        val doctorFilteredSpecialize = runSpecializeFilter(doctorFilteredHospital, listSpecialize)

        listDoctorFiltered = doctorFilteredSpecialize

        notifyDataSetChanged()
    }

    private fun runHospitalFilter(
        listDoctorFoundSearch: ArrayList<Doctor>,
        listHospital: List<Hospital>
    ): ArrayList<Doctor> {
        var listDoctorFoundFirstFilter = ArrayList<Doctor>()

        val numberOfHospitalChecked = listHospital.filter { it.isChecked }

        if(numberOfHospitalChecked.isEmpty()) {
            listDoctorFoundFirstFilter = listDoctorFoundSearch

        } else {
            listDoctorFoundSearch.forEach { doctor ->
                val hospitalFoundByDoctorData =
                    listHospital.find { it.id == doctor.hospital.id && it.isChecked }

                hospitalFoundByDoctorData?.let {
                    listDoctorFoundFirstFilter.add(doctor)
                }
            }
        }

        return listDoctorFoundFirstFilter
    }

    private fun runSpecializeFilter(
        listFiltered: ArrayList<Doctor>,
        listSpecialize: List<Specialization>
    ): ArrayList<Doctor> {

        var listDoctorFoundSecondFilter = ArrayList<Doctor>()

        val numberOfSpecializeChecked = listSpecialize.filter { it.isChecked }

        if(numberOfSpecializeChecked.isEmpty()) {
            listDoctorFoundSecondFilter = listFiltered

        } else {
            listFiltered.forEach { doctor ->
                val specializeFoundByDoctorData = listSpecialize.find { it.id == doctor.specialization.id && it.isChecked }

                specializeFoundByDoctorData?.let {
                    listDoctorFoundSecondFilter.add(doctor)
                }
            }
        }

        return listDoctorFoundSecondFilter
    }
}