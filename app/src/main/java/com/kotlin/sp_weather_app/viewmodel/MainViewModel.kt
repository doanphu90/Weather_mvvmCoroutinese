package com.kotlin.sp_weather_app.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.kotlin_rxjava_coroutines_mvvm.repository.Repository.safeApiCall
import com.kotlin.sp_weather_app.model.DetailWeather
import com.kotlin.sp_weather_app.repository.ResultApi
import com.kotlin.weatherappcoroutines.model.ResponesSearch
import com.kotlin.weatherappcoroutines.retrofit.RetrofitBuilder
import kotlinx.coroutines.*
import java.io.InputStream
import java.net.URL
import java.net.URLEncoder
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel(), CoroutineScope {
    var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val post: MutableLiveData<ResponesSearch> by lazy {
        MutableLiveData<ResponesSearch>()
    }

    val get: MutableLiveData<DetailWeather> by lazy {
        MutableLiveData<DetailWeather>()
    }

    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getResultSearchFromServer(query: String) {
        job.let { completableJob ->
            CoroutineScope(coroutineContext).launch {
                val resulSearch = safeApiCall(call = {
                    RetrofitBuilder.apiInterface.getResultSearch(query)
                })
                when (resulSearch) {
                    is ResultApi.Success -> {
                        post.postValue(resulSearch.data)
                    }
                    is ResultApi.Error -> {
                        errorMessage.postValue(resulSearch.exception)
                    }
                }
            }
        }
    }

    fun getDetailWeatherCity(lonLat: String) {
        job.let { completableJob ->
            CoroutineScope(coroutineContext).launch {
                val resultDetail = safeApiCall(call = {
                    RetrofitBuilder.apiInterface.getResultDetailWeather(lonLat)
                })
                when (resultDetail) {
                    is ResultApi.Success -> {
                        get.postValue(resultDetail.data)
                    }
                    is ResultApi.Error -> {
                        errorMessage.postValue(resultDetail.exception)
                    }
                }
            }
        }
    }

    fun getImageWeather(url: String): Deferred<Bitmap?> {
        return CoroutineScope(Dispatchers.IO).async {
            var img: Bitmap? = null
            if (url.isNotEmpty()) {
                img = decodeImage(url)
            }
            img
        }
    }

    private fun decodeImage(urlImg: String): Bitmap {
        var img: Bitmap? = null
        try {
            val input: InputStream = URL(urlImg).openStream()
            img = BitmapFactory.decodeStream(input)
        } catch (e: java.lang.Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }
        return img!!
    }

    fun cancelJob() {
        job?.cancel()
    }

}