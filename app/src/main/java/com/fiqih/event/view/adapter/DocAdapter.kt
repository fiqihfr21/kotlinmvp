package com.fiqih.event.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fiqih.event.model.Document
import com.fiqih.event.view.viewholder.DocViewHolder

class DocAdapter(private val list: List<Document>, val clickListener : (Document)->Unit) : RecyclerView.Adapter<DocViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DocViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        return DocViewHolder(inflater, p0)
    }

    override fun onBindViewHolder(p0: DocViewHolder, p1: Int) {
        val mDocument = list[p1]
        p0.bind(mDocument, clickListener)
    }

    override fun getItemCount(): Int = list.size
}