package com.fiqih.event.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fiqih.event.model.Banner
import com.fiqih.event.view.viewholder.HomeBannerViewHolder

class HomeBannerAdapter(private val list: List<Banner>, val clickListener: (Banner) -> Unit):RecyclerView.Adapter<HomeBannerViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HomeBannerViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        return HomeBannerViewHolder(inflater,p0)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: HomeBannerViewHolder, p1: Int) {
        val banner : Banner = list[p1]
        p0.bind(banner, clickListener)
    }

}