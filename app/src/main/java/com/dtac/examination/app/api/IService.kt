package com.dtac.examination.app.api

import com.dtac.examination.app.ui.profile.model.ProfileResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IService {

    @GET("api/")
    fun getProfile(@Query("results") results: Int) : Call<ProfileResponseModel>
}