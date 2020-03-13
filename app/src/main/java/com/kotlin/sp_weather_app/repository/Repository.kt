package com.kotlin.kotlin_rxjava_coroutines_mvvm.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.kotlin.sp_weather_app.repository.ResultApi
import kotlinx.coroutines.*
import retrofit2.Response

object Repository {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResultApi<T> {
        return try {
            val myResp = call.invoke()
            if (myResp.isSuccessful) {
                ResultApi.Success(myResp.body()!!)
            } else {
                if (myResp.code() == 403) {
                    Log.i("responseCode", "Authentication failed")
                }

                ResultApi.Error(myResp.errorBody()?.string() ?: "api wrong")
            }

        } catch (e: Exception) {
            ResultApi.Error(e.message ?: "Internet error runs")
        }
    }
}