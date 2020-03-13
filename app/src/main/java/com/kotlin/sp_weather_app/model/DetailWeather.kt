package com.kotlin.sp_weather_app.model


import com.google.gson.annotations.SerializedName

data class DetailWeather(
    val `data`: Data
)

data class Data(
    @SerializedName("ClimateAverages")
    val climateAverages: List<ClimateAverage>,
    @SerializedName("current_condition")
    val currentCondition: List<CurrentCondition>,
    val request: List<Request>,
    val weather: List<Weather>
)

data class ClimateAverage(
    val month: List<Month>
)

data class Month(
    val absMaxTemp: String,
    @SerializedName("absMaxTemp_F")
    val absMaxTempF: String,
    val avgDailyRainfall: String,
    val avgMinTemp: String,
    @SerializedName("avgMinTemp_F")
    val avgMinTempF: String,
    val index: String,
    val name: String
)

data class CurrentCondition(
    val cloudcover: String,
    @SerializedName("FeelsLikeC")
    val feelsLikeC: String,
    @SerializedName("FeelsLikeF")
    val feelsLikeF: String,
    val humidity: String,
    @SerializedName("observation_time")
    val observationTime: String,
    val precipInches: String,
    val precipMM: String,
    val pressure: String,
    val pressureInches: String,
    @SerializedName("temp_C")
    val tempC: String,
    @SerializedName("temp_F")
    val tempF: String,
    val uvIndex: Int,
    val visibility: String,
    val visibilityMiles: String,
    val weatherCode: String,
    val weatherDesc: List<WeatherDesc>,
    val weatherIconUrl: List<WeatherIconUrl>,
    val winddir16Point: String,
    val winddirDegree: String,
    val windspeedKmph: String,
    val windspeedMiles: String
)

data class WeatherDesc(
    val value: String
)

data class WeatherIconUrl(
    val value: String
)

data class Request(
    val query: String,
    val type: String
)

data class Weather(
    val astronomy: List<Astronomy>,
    val avgtempC: String,
    val avgtempF: String,
    val date: String,
    val hourly: List<Hourly>,
    val maxtempC: String,
    val maxtempF: String,
    val mintempC: String,
    val mintempF: String,
    val sunHour: String,
    @SerializedName("totalSnow_cm")
    val totalSnowCm: String,
    val uvIndex: String
)

data class Astronomy(
    @SerializedName("moon_illumination")
    val moonIllumination: String,
    @SerializedName("moon_phase")
    val moonPhase: String,
    val moonrise: String,
    val moonset: String,
    val sunrise: String,
    val sunset: String
)

data class Hourly(
    val chanceoffog: String,
    val chanceoffrost: String,
    val chanceofhightemp: String,
    val chanceofovercast: String,
    val chanceofrain: String,
    val chanceofremdry: String,
    val chanceofsnow: String,
    val chanceofsunshine: String,
    val chanceofthunder: String,
    val chanceofwindy: String,
    val cloudcover: String,
    @SerializedName("DewPointC")
    val dewPointC: String,
    @SerializedName("DewPointF")
    val dewPointF: String,
    @SerializedName("FeelsLikeC")
    val feelsLikeC: String,
    @SerializedName("FeelsLikeF")
    val feelsLikeF: String,
    @SerializedName("HeatIndexC")
    val heatIndexC: String,
    @SerializedName("HeatIndexF")
    val heatIndexF: String,
    val humidity: String,
    val precipInches: String,
    val precipMM: String,
    val pressure: String,
    val pressureInches: String,
    val tempC: String,
    val tempF: String,
    val time: String,
    val uvIndex: String,
    val visibility: String,
    val visibilityMiles: String,
    val weatherCode: String,
    @SerializedName("WindChillC")
    val windChillC: String,
    @SerializedName("WindChillF")
    val windChillF: String,
    @SerializedName("WindGustKmph")
    val windGustKmph: String,
    @SerializedName("WindGustMiles")
    val windGustMiles: String,
    val winddir16Point: String,
    val winddirDegree: String,
    val windspeedKmph: String,
    val windspeedMiles: String
)