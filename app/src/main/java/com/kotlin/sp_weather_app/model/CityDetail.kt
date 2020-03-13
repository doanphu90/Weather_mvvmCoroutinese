package com.kotlin.sp_weather_app.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityDetail(
    val areaName: String,
    val country: String,
    val latitude: String,
    val longitude: String,
    val population: String
) : Parcelable