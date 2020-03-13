package com.kotlin.sp_weather_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlin.sp_weather_app.R
import com.kotlin.sp_weather_app.model.CityDetail
import com.kotlin.sp_weather_app.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var itemCity: CityDetail
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        itemCity = intent.extras?.getParcelable<CityDetail>("SelectCity")!!
        tv_title.text = itemCity.areaName + " - " + itemCity.country
        //
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        ll_title.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        setUpViewModel()
    }

    fun setUpViewModel() {
        viewModel.getDetailWeatherCity(itemCity.latitude + "," + itemCity.longitude)

        viewModel.get.observe(this, Observer {
            var currentCondi = it.data.currentCondition
            tv_humidity.text = currentCondi.get(0).humidity
            tv_temp.text = currentCondi.get(0).tempC + " oC"
            var url = currentCondi.get(0).weatherIconUrl.get(0).value
            CoroutineScope(Dispatchers.Main).launch {
                im_wearther.setImageBitmap(viewModel.getImageWeather(url).await())
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }
}
