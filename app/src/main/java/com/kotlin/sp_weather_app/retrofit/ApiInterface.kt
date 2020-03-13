package com.kotlin.weatherappcoroutines.retrofit

import com.kotlin.sp_weather_app.model.DetailWeather
import com.kotlin.weatherappcoroutines.model.ResponesSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("search.ashx?format=json&key=6fe0e24c40354453beb152141202702")
    suspend fun getResultSearch(@Query("query") query: String): Response<ResponesSearch>

    @GET("weather.ashx?format=json&key=6fe0e24c40354453beb152141202702&date=today")
    suspend fun getResultDetailWeather(@Query("q") userId: String): Response<DetailWeather>
}