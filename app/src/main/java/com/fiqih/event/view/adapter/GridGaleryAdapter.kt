package com.fiqih.event.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fiqih.event.model.Galery
import com.fiqih.event.model.itemGalery
import com.fiqih.event.view.viewholder.GridGaleryViewHolder

class GridGaleryAdapter(private val list: List<itemGalery>, val clickListener : (itemGalery)->Unit) : RecyclerView.Adapter<GridGaleryViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GridGaleryViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        return GridGaleryViewHolder(inflater, p0)
    }

    override fun onBindViewHolder(p0: GridGaleryViewHolder, p1: Int) {
        val mGalery = list[p1]
        p0.bind(mGalery, clickListener)
    }

    override fun getItemCount(): Int = list.size
}