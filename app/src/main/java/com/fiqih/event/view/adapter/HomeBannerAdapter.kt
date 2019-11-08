package com.fiqih.event.view.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.fiqih.event.R
import com.fiqih.event.model.Banner
import com.fiqih.event.view.activity.MainActivity
import com.fiqih.event.view.viewholder.HomeBannerViewHolder
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.support.annotation.NonNull
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.view.PagerAdapter
import android.view.View
import android.widget.ImageView
import com.fiqih.event.model.itemBanner
import com.fiqih.event.rest.APIService
import com.squareup.picasso.Picasso


class HomeBannerAdapter(internal var mContext: Context, internal var mListBanner: List<itemBanner>) : PagerAdapter() {

    @NonNull
    override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutBanner = inflater.inflate(R.layout.layout_banner_home, null)

        val imgBanner = layoutBanner.findViewById<ImageView>(R.id.img_banner_home)

        Picasso.get().load(mListBanner[position].banner_image)
            //.placeholder(R.drawable.background_image_round)
            //.transform(CircleTransform())
            //.error(R.drawable.background_image_round)
            .into(imgBanner)
        //imgBanner.setImageResource(mListBanner[position].image)
        container.addView(layoutBanner)

        return layoutBanner
    }

    override fun isViewFromObject(@NonNull view: View, @NonNull o: Any): Boolean {
        return view === o
    }

    override fun destroyItem(@NonNull container: ViewGroup, position: Int, @NonNull `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return mListBanner.size
    }
}