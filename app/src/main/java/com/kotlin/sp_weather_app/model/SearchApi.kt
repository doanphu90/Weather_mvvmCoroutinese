package com.kotlin.weatherappcoroutines.model


import com.google.gson.annotations.SerializedName

data class SearchApi(
    val result: List<ResultInfo>
)