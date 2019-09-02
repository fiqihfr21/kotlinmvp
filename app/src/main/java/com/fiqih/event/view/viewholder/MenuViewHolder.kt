package com.fiqih.event.view.viewholder

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.fiqih.event.R

class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    internal var tv_menu_title: TextView
    internal var img_menu: ImageView
    internal var cardViewMenu: CardView

    init {

        tv_menu_title = itemView.findViewById(R.id.id_menu_title)
        img_menu = itemView.findViewById(R.id.id_menu_img)
        cardViewMenu = itemView.findViewById(R.id.cardview_menu_id)
    }

}