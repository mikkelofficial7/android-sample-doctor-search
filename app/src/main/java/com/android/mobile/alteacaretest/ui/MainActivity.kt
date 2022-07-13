package com.android.mobile.alteacaretest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.mobile.alteacaretest.R
import com.android.mobile.alteacaretest.databinding.ActivityMainBinding
import com.android.mobile.alteacaretest.ui.adapter.DoctorListAdapter
import com.android.mobile.alteacaretest.model.detail.Doctor
import com.android.mobile.alteacaretest.model.detail.Hospital
import com.android.mobile.alteacaretest.model.detail.Specialization
import com.android.mobile.alteacaretest.room.RoomBuilder
import com.android.mobile.alteacaretest.state.UIState
import com.android.mobile.alteacaretest.ui.adapter.SpinnerHospitalAdapter
import com.android.mobile.alteacaretest.ui.adapter.SpinnerSpecializationAdapter
import com.android.mobile.alteacaretest.viewmodel.DoctorViewModel
import java.util.HashSet

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DoctorListAdapter

    private val viewModel: DoctorViewModel by lazy {
        ViewModelProvider(this)[DoctorViewModel::class.java]
    }

    private var listSpecialization = ArrayList<Specialization>()
    private var listHospital = ArrayList<Hospital>()
    private var lastSearch = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        loadAllDoctor()
        setupListener()
    }

    private fun setupListener() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                lastSearch = s.toString()
                filterDoctor()
            }

        })

        SpinnerHospitalAdapter.onHospitalSelect = { hospital ->
            listHospital = hospital as ArrayList<Hospital>

            filterDoctor()
        }

        SpinnerSpecializationAdapter.onSpecializeSelect = { specialize ->
            listSpecialization = specialize as ArrayList<Specialization>

            filterDoctor()
        }
    }

    private fun loadAllDoctor() {
        val dao = RoomBuilder.init(this)?.responseDao()

        if(dao?.getResponseData()?.doctorList?.isNotEmpty() == true) {
            addDataDoctor(dao.getResponseData().doctorList)
            setDataFilter(dao.getResponseData().doctorList)
        } else {
            viewModel.getDoctorLiveData().observe(this, Observer { state ->
                when(state) {
                    is UIState.Loading -> {
                        showLoading()
                    }
                    is UIState.SuccessLoad -> {
                        dao?.insertAllDoctor(state.response)

                        addDataDoctor(state.response.doctorList)
                        setDataFilter(state.response.doctorList)

                        hideLoading()
                    }
                    is UIState.FailedLoad -> {
                        showErrorMessage()

                        hideLoading()
                    }
                }
            })
        }
    }

    private fun addDataDoctor(doctorList: ArrayList<Doctor>) {
        adapter.addData(doctorList)
    }

    private fun setDataFilter(doctorList: ArrayList<Doctor>) {
        val hashSpecialization = HashSet<Specialization>()
        val hashHospital = HashSet<Hospital>()

        listHospital.add(Hospital(name = getString(R.string.hospital_filter)))
        listSpecialization.add(Specialization(name = getString(R.string.specialization_filter)))

        doctorList.forEach {
            hashSpecialization.add(it.specialization)
            hashHospital.add(it.hospital)
        }

        listSpecialization.addAll(ArrayList(hashSpecialization))
        listHospital.addAll(ArrayList(hashHospital))

        setSpinnerData()
    }

    private fun setSpinnerData() {
        val hospitalAdapter = SpinnerHospitalAdapter(this, listHospital)
        binding.spinnerHospital.adapter = hospitalAdapter

        val specializationAdapter = SpinnerSpecializationAdapter(this, listSpecialization)
        binding.spinnerSpecialization.adapter = specializationAdapter
    }

    private fun filterDoctor() {
        checkAdapterIsInitialize()
        adapter.setFilterDoctor(listHospital, listSpecialization, lastSearch)
    }

    private fun initAdapter() {
        checkAdapterIsInitialize()

        adapter = DoctorListAdapter()
        binding.rvDoctor.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvDoctor.adapter = adapter
    }

    private fun checkAdapterIsInitialize() {
        if(::adapter.isInitialized) return
    }

    private fun showLoading() {
        binding.pbLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.pbLoading.visibility = View.GONE
    }

    private fun showErrorMessage() {
        Toast.makeText(this, getString(R.string.failed_load_doctor), Toast.LENGTH_LONG).show()
    }
}