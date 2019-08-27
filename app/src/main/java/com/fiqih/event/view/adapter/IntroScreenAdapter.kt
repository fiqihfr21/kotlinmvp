package com.fiqih.event.view.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.fiqih.event.R
import com.fiqih.event.model.Banner
import com.fiqih.event.model.ScreenItem
import com.fiqih.event.model.itemScreen
import com.fiqih.event.rest.APIService.BASE_URL_GENERAL
import com.fiqih.event.util.CircleTransform
import com.squareup.picasso.Picasso

class IntroScreenAdapter(internal var mContext: Context, internal var mListScreen: List<itemScreen>) :
    PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutScreen = inflater.inflate(R.layout.layout_screen, null)

        val imgSlide = layoutScreen.findViewById<ImageView>(R.id.img_screen_intro)
        val title = layoutScreen.findViewById<TextView>(R.id.txt_title_intro)
        val description = layoutScreen.findViewById<TextView>(R.id.txt_description_intro)

        Log.i("list data : ", mListScreen[position].toString())
        title.setText(mListScreen[position].splash_title)
        description.setText(mListScreen[position].splash_desc)
        Picasso.get().load(BASE_URL_GENERAL+"/img/"+mListScreen[position].splash_image)
            //.placeholder(R.drawable.background_image_round)
            //.transform(CircleTransform())
            //.error(R.drawable.background_image_round)
            .into(imgSlide)
//        imgSlide.setImageResource(mListScreen[position].image)

        container.addView(layoutScreen)

        return layoutScreen

    }

    override fun getCount(): Int {
        return mListScreen.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view == o
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
