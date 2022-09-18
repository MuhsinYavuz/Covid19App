package com.muhsinyavuz.covid19trackingapplication.service


import com.muhsinyavuz.covid19trackingapplication.model.Model
import com.muhsinyavuz.covid19trackingapplication.model.Statistic
import io.reactivex.Single


import retrofit2.http.GET


interface CountryAPI {
    @GET("MuhsinYavuz/e0640d4b8ed89c969534b44237e01df8/raw/c743e2c52524ec3dbc8ca44c972747bc9a3acff8/covid19.json")
    fun getCountries(): Single<Model>

}