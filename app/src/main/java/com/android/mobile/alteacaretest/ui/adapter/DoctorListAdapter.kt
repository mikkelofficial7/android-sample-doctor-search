package com.android.mobile.alteacaretest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.android.mobile.alteacaretest.databinding.ItemDoctorListBinding
import com.android.mobile.alteacaretest.model.detail.Doctor
import com.android.mobile.alteacaretest.model.detail.Hospital
import com.android.mobile.alteacaretest.model.detail.Specialization
import com.android.mobile.alteacaretest.ui.viewholder.DoctorListViewHolder
import java.util.*
import kotlin.collections.ArrayList

class DoctorListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder?>(), Filterable {
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(wordToSearch: CharSequence?): FilterResults {
                val searchWord = wordToSearch?.toString().orEmpty()

                listDoctorFiltered = if (searchWord.isEmpty()) {
                    listDoctor
                } else {
                    val filteredList = ArrayList<Doctor>()

                    listDoctor.filter { doctor ->
                                doctor.name
                                .lowercase(Locale.getDefault())
                                .contains(searchWord)
                    }.forEach { itemDoctor ->
                        filteredList.add(itemDoctor)
                    }

                    filteredList
                }

                return FilterResults().apply { values = listDoctorFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listDoctorFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Doctor>

                notifyDataSetChanged()
            }
        }
    }

    fun getFilterHospital(listHospital: List<Hospital>) {
        listDoctorFiltered = if(listHospital.isEmpty()) {
            listDoctor

        } else {
            val listDoctorFound = ArrayList<Doctor>()
            listDoctor.forEach { doctor ->
                val isHospitalFoundByDoctorData = listHospital.find { it.id == doctor.hospital.id }

                isHospitalFoundByDoctorData?.let {
                    listDoctorFound.add(doctor)
                }
            }

            listDoctorFound
        }

        notifyDataSetChanged()
    }

    fun getFilterSpecialize(listSpecialize: List<Specialization>) {
        listDoctorFiltered = if(listSpecialize.isEmpty()) {
            listDoctor

        } else {
            val listDoctorFound = ArrayList<Doctor>()
            listDoctor.forEach { doctor ->
                val isSpecializeFoundByDoctorData = listSpecialize.find { it.id == doctor.specialization.id }

                isSpecializeFoundByDoctorData?.let {
                    listDoctorFound.add(doctor)
                }
            }

            listDoctorFound
        }

        notifyDataSetChanged()
    }
}