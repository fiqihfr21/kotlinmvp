package com.fiqih.event.view.adapter

import android.content.Context
import android.content.Intent
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiqih.event.R
import com.fiqih.event.model.Menu
import com.fiqih.event.view.activity.*
import com.fiqih.event.view.viewholder.MenuViewHolder

class MenuMoreAdapter(private val mContext: Context, private val mData: List<Menu>) :
    RecyclerView.Adapter<MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view: View
        val mInflater = LayoutInflater.from(mContext)
        view = mInflater.inflate(R.layout.item_grid, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: MenuViewHolder, position: Int) {
        holder.tv_menu_title.setText(mData[position].title)
        holder.img_menu.setImageResource(mData[position].image)

        // Set Click on Listener
        holder.cardViewMenu.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                if (position == 0) {
                    // barcode
//                    val sheet = BSQRFragment()
//                    sheet.show((mContext as AppCompatActivity).supportFragmentManager, "BottomSheetFragment")
                    mContext.startActivity(Intent(mContext, QRActivity::class.java))
                } else if (position == 1) {

                    mContext.startActivity(Intent(mContext, ScheduleActivity::class.java))
                } else if (position == 2) {

                    mContext.startActivity(Intent(mContext, MessageActivity::class.java))
                }else if (position == 3) {

                    mContext.startActivity(Intent(mContext, MapsActivity::class.java))
                }else if (position == 4) {
                    mContext.startActivity(Intent(mContext, GaleryActivity::class.java))
                }else if (position == 5) {
                    mContext.startActivity(Intent(mContext, DocumentActivity::class.java))
                }else if (position == 6) {
                    mContext.startActivity(Intent(mContext, QuizActivity::class.java))
                }else if (position == 7) {
                    mContext.startActivity(Intent(mContext, SouvenirActivity::class.java))
                }else if (position == 8) {
                    mContext.startActivity(Intent(mContext, FeedbackActivity::class.java))
                }else if (position == 9) {
                    mContext.startActivity(Intent(mContext, SurveyActivity::class.java))
                }else if (position == 10) {
                    mContext.startActivity(Intent(mContext, AboutActivity::class.java))
                }else {
                    mContext.startActivity(Intent(mContext, HelpActivity::class.java))
                }

                //dst

            }
        })

    }

    override fun getItemCount(): Int {
        return mData.size
    }

}