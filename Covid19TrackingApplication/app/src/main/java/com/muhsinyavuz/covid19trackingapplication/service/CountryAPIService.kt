package com.muhsinyavuz.covid19trackingapplication.service

import com.muhsinyavuz.covid19trackingapplication.model.Model
import com.muhsinyavuz.covid19trackingapplication.model.Statistic
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



class CountryAPIService {
    private val BASE_URL = "https://gist.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(CountryAPI::class.java)


    fun getData() : Single<Model> {
        return api.getCountries()
    }
}