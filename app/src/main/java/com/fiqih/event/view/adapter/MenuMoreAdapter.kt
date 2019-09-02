package com.fiqih.event.view.adapter

import android.content.Context
import android.content.Intent
import android.support.annotation.NonNull
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fiqih.event.R
import com.fiqih.event.model.Menu
import com.fiqih.event.view.activity.QRActivity
import com.fiqih.event.view.activity.ScheduleActivity
import com.fiqih.event.view.fragment.BSMenuFragment
import com.fiqih.event.view.fragment.BSQRFragment
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
//                    BSMenuFragment().isCancelable
//                    val sheet = BSQRFragment()
//                    sheet.show((mContext as AppCompatActivity).supportFragmentManager, "BottomSheetFragment")
                    mContext.startActivity(Intent(mContext, QRActivity::class.java))
                } else if (position == 1) {
                    // open activity_maps agenda
                    mContext.startActivity(Intent(mContext, ScheduleActivity::class.java))
                } else if (position == 2) {
                    // message
                    //open activity_maps my Booking activity_maps
//                    val intentMyBooking = Intent(mContext, MyBookingActivity::class.java)
//                    mContext.startActivity(intentMyBooking)
                    Toast.makeText(mContext, "ini message", Toast.LENGTH_SHORT).show()
                }else if (position == 3) {
                    // message
                    //open activity_maps my Booking activity_maps
//                    val intentMyBooking = Intent(mContext, MyBookingActivity::class.java)
//                    mContext.startActivity(intentMyBooking)
                    Toast.makeText(mContext, "ini message", Toast.LENGTH_SHORT).show()
                }else if (position == 4) {
                    // message
                    //open activity_maps my Booking activity_maps
//                    val intentMyBooking = Intent(mContext, MyBookingActivity::class.java)
//                    mContext.startActivity(intentMyBooking)
                    Toast.makeText(mContext, "ini message", Toast.LENGTH_SHORT).show()
                }else if (position == 5) {
                    // message
                    //open activity_maps my Booking activity_maps
//                    val intentMyBooking = Intent(mContext, MyBookingActivity::class.java)
//                    mContext.startActivity(intentMyBooking)
                    Toast.makeText(mContext, "ini message", Toast.LENGTH_SHORT).show()
                }else if (position == 6) {
                    // message
                    //open activity_maps my Booking activity_maps
//                    val intentMyBooking = Intent(mContext, MyBookingActivity::class.java)
//                    mContext.startActivity(intentMyBooking)
                    Toast.makeText(mContext, "ini message", Toast.LENGTH_SHORT).show()
                } else {
//                    val intentAgenda = Intent(mContext, DetailsProfileActivity::class.java)
//                    mContext.startActivity(intentAgenda)
                    Toast.makeText(mContext, "ini profile", Toast.LENGTH_SHORT).show()

                }

                //dst

            }
        })

    }

    override fun getItemCount(): Int {
        return mData.size
    }

}