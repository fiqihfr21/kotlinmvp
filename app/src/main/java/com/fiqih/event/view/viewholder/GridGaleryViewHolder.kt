package com.fiqih.event.view.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fiqih.event.R
import com.fiqih.event.model.Galery
import com.fiqih.event.model.itemGalery
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_grid_galery.view.*

class GridGaleryViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_grid_galery, parent, false)){

    private val imgBanner = itemView.im_galery

    fun bind(galery: itemGalery, clickListener: (itemGalery) -> Unit){
        Picasso.get().load(galery.galery_image)
            //.placeholder(R.drawable.background_image_round)
            //.transform(CircleTransform())
            //.error(R.drawable.background_image_round)
            .into(imgBanner)

        itemView.setOnClickListener { clickListener(galery)}
    }
}