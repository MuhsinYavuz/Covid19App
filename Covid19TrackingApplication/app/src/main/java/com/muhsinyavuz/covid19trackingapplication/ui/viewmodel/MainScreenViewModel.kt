package com.muhsinyavuz.covid19trackingapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhsinyavuz.covid19trackingapplication.model.Model
import com.muhsinyavuz.covid19trackingapplication.model.Statistic

import com.muhsinyavuz.covid19trackingapplication.service.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

import io.reactivex.schedulers.Schedulers




class MainScreenViewModel : ViewModel(){
    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()
    val countries = MutableLiveData<Model>()
    val countryError = MutableLiveData<Boolean>()
    val countrLoading = MutableLiveData<Boolean>()


    fun refreshData(){
        getDataFromAPI()

    }

    private fun getDataFromAPI(){
        countrLoading.value = true

        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Model>(){

                    override fun onSuccess(t: Model) {
                        countries.value = t
                        countryError.value = false
                        countrLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        countrLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }
                }
        ))
    }


}




