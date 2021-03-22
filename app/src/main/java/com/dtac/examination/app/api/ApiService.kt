package com.dtac.examination.app.api

import com.dtac.examination.app.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {
    private val TIMEOUT: Long = 30000

    fun createService(): IService {
        var retrofit = Retrofit.Builder()
            .addConverterFactory(addConverter())
            .baseUrl("https://randomuser.me/")
            .client(getClient())
            .build()
        return retrofit.create(IService::class.java)
    }

    private fun getClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        return builder.addInterceptor(getDefaultHttpLogging(BuildConfig.DEBUG))
            .certificatePinner(getDefaultCertificatePinner())
            .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS).build()
    }

    private fun getDefaultHttpLogging(isLog: Boolean): HttpLoggingInterceptor {
        if (isLog) {
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    private fun getDefaultCertificatePinner(): CertificatePinner =
        CertificatePinner.Builder().build()

    private fun addConverter(): Converter.Factory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

}