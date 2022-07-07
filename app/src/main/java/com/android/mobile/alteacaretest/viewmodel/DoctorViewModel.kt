package com.android.mobile.alteacaretest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.mobile.alteacaretest.model.ResponseData
import com.android.mobile.alteacaretest.network.NetworkCall
import com.android.mobile.alteacaretest.network.NetworkInterface
import com.android.mobile.alteacaretest.network.ResponseCallback
import com.android.mobile.alteacaretest.state.UIState

class DoctorViewModel : ViewModel() {
    private var doctorLiveData: MutableLiveData<UIState> = MutableLiveData()

    fun loadDoctor() {
        doctorLiveData.postValue(UIState.Loading)

        MutableLiveData<UIState>().apply {
            val request = NetworkCall
                .provideRequest(NetworkInterface::class.java)
                .loadAllDoctor()

            NetworkCall.process(request, object : ResponseCallback<ResponseData> {
                override fun onSuccess(responseBody: ResponseData) {
                    doctorLiveData.postValue(UIState.SuccessLoad(responseBody))
                }

                override fun onError(errorCode: String, errorMessage: String) {
                    doctorLiveData.postValue(UIState.FailedLoad)
                }
            })
        }
    }

    fun getDoctorLiveData() : MutableLiveData<UIState> {
        return doctorLiveData
    }
}