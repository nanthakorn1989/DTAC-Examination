package com.dtac.examination.app.ui.profile.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dtac.examination.app.api.ApiService
import com.dtac.examination.app.base.BaseViewModel
import com.dtac.examination.app.ui.profile.model.ProfileResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : BaseViewModel() {

    var profileMutableLiveData:  MutableLiveData<ProfileResponseModel> = MutableLiveData()

    fun getProfile(row: Int){
        ApiService().createService().getProfile(row).enqueue(object : Callback<ProfileResponseModel>{
            override fun onResponse(
                call: Call<ProfileResponseModel>,
                response: Response<ProfileResponseModel>
            ) {
                when{
                    response.isSuccessful -> {
                        serviceSuccess(response, profileMutableLiveData)
                    }
                }
            }

            override fun onFailure(call: Call<ProfileResponseModel>, t: Throwable) {
                profileMutableLiveData.value = null
            }
        })
    }
}