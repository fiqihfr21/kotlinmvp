package com.fiqih.event.view.viewholder

import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fiqih.event.R
import com.fiqih.event.model.Banner
import com.fiqih.event.rest.APIService.BASE_URL_STORAGE
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_banner.view.*

class HomeBannerViewHolder(inflater: LayoutInflater, parent: ViewGroup):RecyclerView.ViewHolder(inflater.inflate(R.layout.item_home_banner, parent, false)){

    private val imgBanner = itemView.imviewbanner

    fun bind(banner: Banner, clickListener: (Banner) -> Unit){
        Picasso.get().load(BASE_URL_STORAGE +"img/post/"+banner.image)
            //.placeholder(R.drawable.background_image_round)
            //.transform(CircleTransform())
            //.error(R.drawable.background_image_round)
            .into(imgBanner)

        itemView.setOnClickListener { clickListener(banner)}
    }
}