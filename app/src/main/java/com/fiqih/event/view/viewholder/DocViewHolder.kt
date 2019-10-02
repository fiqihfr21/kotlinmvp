package com.fiqih.event.view.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fiqih.event.R
import com.fiqih.event.model.Document
class DocViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_doc, parent, false)){

    //private val imgBanner = itemView.im

    fun bind(galery: Document, clickListener: (Document) -> Unit){
//        Picasso.get().load(galery.image)
//            //.placeholder(R.drawable.background_image_round)
//            //.transform(CircleTransform())
//            //.error(R.drawable.background_image_round)
//            .into(imgBanner)

        itemView.setOnClickListener { clickListener(galery)}
    }
}