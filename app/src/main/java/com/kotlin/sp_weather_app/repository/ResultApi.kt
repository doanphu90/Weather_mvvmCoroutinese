package com.kotlin.sp_weather_app.repository

sealed class ResultApi<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultApi<T>()
    data class Error(val exception: String) : ResultApi<Nothing>()
}