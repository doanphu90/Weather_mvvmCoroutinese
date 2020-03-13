package com.kotlin.sp_weather_app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.kotlin.sp_weather_app.utils.AppConstant

class WeatherApplication : Application(){
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        sharePreference = getSharedPreferences(AppConstant.SHAREDPREFERENCES, Context.MODE_PRIVATE)
    }

    companion object {
        lateinit var sharePreference: SharedPreferences
        private var instance: WeatherApplication? = null

        fun applicationContext(): WeatherApplication {
            return instance as WeatherApplication
        }
    }
}