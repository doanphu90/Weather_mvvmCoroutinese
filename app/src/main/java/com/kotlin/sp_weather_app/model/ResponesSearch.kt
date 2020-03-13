package com.kotlin.weatherappcoroutines.model


import com.google.gson.annotations.SerializedName

data class ResponesSearch(
    @SerializedName("search_api")
    val searchApi: SearchApi
)