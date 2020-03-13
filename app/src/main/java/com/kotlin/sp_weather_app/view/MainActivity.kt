package com.kotlin.sp_weather_app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.sp_weather_app.Interface.IOnClickListenItem
import com.kotlin.sp_weather_app.R
import com.kotlin.sp_weather_app.adapter.SearchWeartherAdapter
import com.kotlin.sp_weather_app.data.HistoryCity.Companion.loadFromStorage
import com.kotlin.sp_weather_app.data.HistoryCity.Companion.savePrefCitySearch
import com.kotlin.sp_weather_app.model.CityDetail
import com.kotlin.sp_weather_app.viewmodel.MainViewModel
import com.kotlin.weatherappcoroutines.model.ResultInfo
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: SearchWeartherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setUpAdapter()
        searView(sv_Search)
    }

    override fun onResume() {
        super.onResume()
        sv_Search.setQuery("", false)
        sv_Search.clearFocus()
        if (loadFromStorage().size > 0) {
            tv_recent.text = getString(R.string.recent_search)
            adapter.updateData(loadFromStorage())
        } else {
            tv_recent.text = getString(R.string.not_recent_search)
        }
    }

    fun searView(searchView: SearchView) {
        searchView.queryHint = getString(R.string.input_text)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("DoanPhu", "onQueryTextSubmit")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                setUpViewModel(newText.toString())
                Log.d("DoanPhu", "onQueryTextChange")
                return true
            }
        })

        searchView.setOnClickListener {
            searchView.onActionViewExpanded()
            Log.d("DoanPhu", "onActionViewExpanded")
        }

        searchView.setOnCloseListener {
            Log.d("DoanPhu", "setOnCloseListener")
            searchView.onActionViewCollapsed()
            true
        }
    }

    fun setUpViewModel(query: String) {
        if (query.isNullOrEmpty()) {
            if (loadFromStorage().size > 0) {
                adapter.updateData(loadFromStorage())
                tv_recent.text = getString(R.string.recent_search)
            } else {
                adapter.updateData(ArrayList())
                tv_recent.text = getString(R.string.not_recent_search)
            }
        } else if (query.length >= 3) {
            val queryEncode = URLEncoder.encode(query, "UTF-8")
            viewModel.getResultSearchFromServer(queryEncode)
            viewModel.post.observe(this, Observer {
                Log.d("DoanPhu", "get data: $it")
                tv_recent.text = ""
                adapter.updateData(it.searchApi.result)
            })

            viewModel.errorMessage.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun setUpAdapter() {
        re_View.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = SearchWeartherAdapter(arrayListOf(), object : IOnClickListenItem {
            override fun onClickItemListener(itemSearch: ResultInfo, position: Int) {
                Log.d("DoanPhu", "name: ${itemSearch.areaName}")

                var selectItem = CityDetail(
                    itemSearch.areaName.get(0).value,
                    itemSearch.country.get(0).value,
                    itemSearch.latitude,
                    itemSearch.longitude,
                    itemSearch.population
                )
                savePrefCitySearch(itemSearch, position)
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("SelectCity", selectItem)
                startActivity(intent)
            }
        })
        re_View.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }
}
