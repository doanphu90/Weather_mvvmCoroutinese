package com.kotlin.sp_weather_app.Interface

import com.kotlin.weatherappcoroutines.model.ResultInfo


interface IOnClickListenItem {
    fun onClickItemListener(item: ResultInfo, position:Int)
}