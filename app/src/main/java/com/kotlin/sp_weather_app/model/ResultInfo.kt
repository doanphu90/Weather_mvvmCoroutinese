package com.kotlin.weatherappcoroutines.model


import com.google.gson.annotations.SerializedName

data class ResultInfo(
    val areaName: List<AreaName>,
    val country: List<Country>,
    val latitude: String,
    val longitude: String,
    val population: String
)