package com.fiqih.event.view.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.fiqih.event.R
import com.fiqih.event.model.itemDocument

class DocViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_doc, parent, false)){

    internal var tv_doc_title: TextView

    init {
        tv_doc_title = itemView.findViewById(R.id.tv_title_doc)
    }

    fun bind(document: itemDocument, clickListener: (itemDocument) -> Unit){

        tv_doc_title.text = document.file_desc

//        Picasso.get().load(galery.image)
//            //.placeholder(R.drawable.background_image_round)
//            //.transform(CircleTransform())
//            //.error(R.drawable.background_image_round)
//            .into(imgBanner)

        itemView.setOnClickListener { clickListener(document)}
    }
}