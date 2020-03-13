package com.kotlin.sp_weather_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.sp_weather_app.Interface.IOnClickListenItem
import com.kotlin.sp_weather_app.R
import com.kotlin.weatherappcoroutines.model.ResultInfo
import kotlinx.android.synthetic.main.item_layout_result_search.view.*

class SearchWeartherAdapter(
    private var listSearch: MutableList<ResultInfo>,
    private var onClickItem: IOnClickListenItem
) : RecyclerView.Adapter<SearchWeartherAdapter.SearchViewHolder>() {

    class SearchViewHolder(itemView: View, private val itemClick: IOnClickListenItem) :
        RecyclerView.ViewHolder(itemView) {
        fun binData(item: ResultInfo, position: Int) {
            itemView.tv_city.text = item.areaName.get(0).value
            itemView.tv_country.text = item.country.get(0).value
            itemView.ll_item.setOnClickListener { itemClick.onClickItemListener(item, position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_result_search, parent, false)
        return SearchViewHolder(v, onClickItem)
    }

    override fun getItemCount(): Int {
        return Math.min(listSearch.size, 10)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.binData(listSearch.get(position), position)
    }

    fun updateData(liUpdate: List<ResultInfo>) {
        listSearch.clear()
        listSearch.addAll(liUpdate)
        notifyDataSetChanged()
    }
}