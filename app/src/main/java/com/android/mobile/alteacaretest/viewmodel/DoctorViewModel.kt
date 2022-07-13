package com.android.mobile.alteacaretest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mobile.alteacaretest.network.repository.DoctorRepository
import com.android.mobile.alteacaretest.state.UIState
import kotlinx.coroutines.launch

class DoctorViewModel @ViewModelInject constructor(private val repository: DoctorRepository) : ViewModel() {
    private var doctorLiveData = MutableLiveData<UIState>()

    fun getDoctorLiveData(): MutableLiveData<UIState> {
        viewModelScope.launch {
            doctorLiveData.postValue(UIState.Loading)

            repository.getAllDoctor().let {
                if(it.isSuccessful)  {
                    it.body()?.let { data ->
                        doctorLiveData.postValue(UIState.SuccessLoad(data))
                    }
                }
                else {
                    doctorLiveData.postValue(UIState.FailedLoad)
                }
            }
        }

        return doctorLiveData
    }
}