package com.kotlin.sp_weather_app.data

import com.kotlin.sp_weather_app.WeatherApplication
import com.kotlin.sp_weather_app.utils.AppConstant
import com.kotlin.weatherappcoroutines.model.AreaName
import com.kotlin.weatherappcoroutines.model.Country
import com.kotlin.weatherappcoroutines.model.ResultInfo
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class HistoryCity {
    companion object {
        fun savePrefCitySearch(item: ResultInfo, positon: Int) {
            var editor = WeatherApplication.sharePreference.edit()

//            get list history, when add item select into top list
            var listHistory: MutableList<ResultInfo> = loadFromStorage()
            if (listHistory.isNullOrEmpty()) {
                listHistory = arrayListOf()
            }
            if (listHistory.contains(item)) {
                for (items in listHistory){
                    if(items.equals(item)){
                        listHistory.remove(items)
                        break
                    }
                }
//                listHistory.removeAt(positon)
                listHistory.add(0, item)
            } else {
                listHistory.add(0, item)
            }
            var jsonObject = convertListToJson(listHistory)
            var setHis: MutableSet<String> = hashSetOf()

            setHis.add(jsonObject.toString())
            editor.clear()
            editor.putStringSet(AppConstant.KEY_HISTORY, HashSet(setHis))
            editor.commit()
        }

        //Exist set
        fun getSharedReference(): MutableSet<String>? {
            var set: MutableSet<String>? =
                WeatherApplication.sharePreference.getStringSet(
                    AppConstant.KEY_HISTORY,
                    HashSet()
                )
            return set
        }

        fun convertListToJson(list: List<ResultInfo>): JSONObject {
            val contactsObj = JSONObject()
            val contactsArray = JSONArray()
            try {
                for (i in 0 until list.size) {
                    var obj = JSONObject()
                    var areaObj = JSONObject()
                    var areaArr = JSONArray()
                    var conObj = JSONObject()
                    var conArr = JSONArray()

                    areaObj.put("value", list.get(i).areaName.get(0).value)
                    areaArr.put(areaObj)
                    obj.put("areaName", areaArr)
                    conObj.put("value", list.get(i).country.get(0).value)
                    conArr.put(conObj)
                    obj.put("country", conArr)
                    obj.put("latitude", list.get(i).latitude)
                    obj.put("longitude", list.get(i).longitude)
                    obj.put("population", list.get(i).population)
                    contactsArray.put(i, obj)
                }
                contactsObj.put("result", contactsArray)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return contactsObj
        }

        fun getJsonObject(item: ResultInfo): JSONObject {
            var obj = JSONObject()
            var areaObj = JSONObject()
            var areaArr = JSONArray()
            var conObj = JSONObject()
            var conArr = JSONArray()
            var regObj = JSONObject()
            var regArr = JSONArray()
            try {
                areaObj.put("value", item.areaName.get(0).value)
                areaArr.put(areaObj)
                obj.put("areaName", areaArr)
                conObj.put("value", item.country.get(0).value)
                conArr.put(conObj)
                obj.put("country", conArr)
                obj.put("latitude", item.latitude)
                obj.put("longitude", item.longitude)
                obj.put("population", item.population)
//                regObj.put("value", item.region.get(0).value)
//                regArr.put(regObj)
                obj.put("region", regObj)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return obj
        }

        //
        fun loadFromStorage(): ArrayList<ResultInfo> {
            var liItems: ArrayList<ResultInfo> = ArrayList<ResultInfo>();

            var set: MutableSet<String>? = WeatherApplication.sharePreference.getStringSet(
                AppConstant.KEY_HISTORY,
                HashSet()
            )
            if (set != null) {
                for (s in set) {
                    try {
                        var jsonObject = JSONObject(s)
                        var stringRoot = jsonObject.getString("result")
                        var jsonRoot = JSONArray(stringRoot)
                        for (i in 0 until jsonRoot.length()) {
                            var jsonChild = jsonRoot.getJSONObject(i)
                            var areaRoot = jsonChild.getJSONArray("areaName")
                            var areaJson = areaRoot.getJSONObject(0)
                            var areaVa = areaJson.getString("value")
                            var countryRoot = jsonChild.getJSONArray("country")
                            var countryJson = countryRoot.getJSONObject(0)
                            var coun = countryJson.getString("value")
                            var latitude = jsonChild.getString("latitude")
                            var longitude = jsonChild.getString("longitude")
                            var population = jsonChild.getString("population")

                            var liArea: MutableList<AreaName> = arrayListOf()
                            var areaName = AreaName(areaVa)
                            liArea.add(areaName)
                            var liCount: MutableList<Country> = arrayListOf()
                            var country = Country(coun)
                            liCount.add(country)
                            var resultInfo = ResultInfo(
                                liArea, liCount, latitude, longitude,
                                population
                            )
                            liItems.add(resultInfo)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
            return liItems
        }
    }
}
