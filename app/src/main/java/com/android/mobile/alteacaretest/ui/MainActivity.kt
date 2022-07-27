package com.android.mobile.alteacaretest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.mobile.alteacaretest.R
import com.android.mobile.alteacaretest.databinding.ActivityMainBinding
import com.android.mobile.alteacaretest.databinding.ItemLastSearchBinding
import com.android.mobile.alteacaretest.model.DoctorCache
import com.android.mobile.alteacaretest.ui.adapter.DoctorListAdapter
import com.android.mobile.alteacaretest.model.detail.Doctor
import com.android.mobile.alteacaretest.model.detail.Hospital
import com.android.mobile.alteacaretest.model.detail.Specialization
import com.android.mobile.alteacaretest.room.RoomHelper
import com.android.mobile.alteacaretest.state.UIState
import com.android.mobile.alteacaretest.ui.adapter.SpinnerHospitalAdapter
import com.android.mobile.alteacaretest.ui.adapter.SpinnerSpecializationAdapter
import com.android.mobile.alteacaretest.viewmodel.DoctorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.HashSet
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter: DoctorListAdapter by lazy {
        DoctorListAdapter()
    }

    private val viewModel: DoctorViewModel by viewModels()

    @Inject
    lateinit var roomHelper: RoomHelper

    private var listSpecialization = ArrayList<Specialization>()
    private var listHospital = ArrayList<Hospital>()
    private var lastSearch = ""
    private lateinit var typingCountDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        addViewLastSearch()
        loadAllDoctor()
        setupListener()
    }

    private fun setupListener() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (::typingCountDownTimer.isInitialized) typingCountDownTimer.cancel()

                typingCountDownTimer = object : CountDownTimer(2000, 1000){
                    override fun onTick(millisUntilFinished: Long) {
                    }

                    override fun onFinish() {
                        lastSearch = s.toString()
                        filterDoctor()

                        if(lastSearch.isEmpty()) return

                        CoroutineScope(Dispatchers.IO).launch {
                            val cache = DoctorCache(doctorName = lastSearch)

                            val totalLastCache = roomHelper.initDoctorCacheDao()?.getAllDoctorCache()?.size ?: 0

                            if(totalLastCache >= 3) {
                                val firstRowCache = roomHelper.initDoctorCacheDao()?.getFirstRowDoctorCache()

                                firstRowCache?.let {
                                    roomHelper.initDoctorCacheDao()?.deleteDoctorCache(it)
                                }
                            }

                            roomHelper.initDoctorCacheDao()?.insertAllDoctorCache(cache)


                            withContext(Dispatchers.Main) {
                                addViewLastSearch()
                            }
                        }
                    }

                }

                typingCountDownTimer.start()
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

    private fun addViewLastSearch() {
        val listCache = roomHelper.initDoctorCacheDao()?.getAllDoctorCache()

        if(listCache.isNullOrEmpty()) return

        binding.viewLastSearch.removeAllViews()

        listCache.forEach {
            binding.viewLastSearch.addView(listOfLastDoctorSearch(it))
        }
        binding.viewLastSearch.requestLayout()

        binding.tvLastSearchTitle.text = getString(R.string.last_search)
    }

    private fun listOfLastDoctorSearch(doctorCache: DoctorCache) : View {
        val itemView = ItemLastSearchBinding.inflate(LayoutInflater.from(this))
        itemView.tvName.text = doctorCache.doctorName
        return itemView.root
    }

    private fun loadAllDoctor() {
        val dao = roomHelper.initDoctorDao()

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
        adapter.setFilterDoctor(listHospital, listSpecialization, lastSearch)
    }

    private fun initAdapter() {
        binding.rvDoctor.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvDoctor.setHasFixedSize(true)
        binding.rvDoctor.adapter = adapter
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