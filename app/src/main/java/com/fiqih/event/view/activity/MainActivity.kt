package com.fiqih.event.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fiqih.event.R
import com.fiqih.event.rest.APIService.BASE_URL_USER
import com.fiqih.event.util.CircleTransform
import com.fiqih.event.util.SessionManager
import com.fiqih.event.view.adapter.HomeBannerAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Handler
import android.support.design.widget.BottomSheetBehavior
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import android.support.v4.view.ViewPager
import com.fiqih.event.model.Banner
import java.util.*
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.fiqih.event.model.Menu
import com.fiqih.event.view.adapter.MenuAdapter
import kotlinx.android.synthetic.main.bottom_sheet_grid.*


class MainActivity : AppCompatActivity() {

    private var viewPagerBannerHome: ViewPager? = null
    internal lateinit var bannerHomeViewPagerAdapter: HomeBannerAdapter
    internal lateinit var dotsIndicator: DotsIndicator
    internal var position = 0
    private var current_position = 0
    private var timer: Timer? = null

    override fun onStart() {
        super.onStart()
        if(!SessionManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        btn_logout.setOnClickListener {
//            SessionManager.getInstance(this).clear()
//            val intent = Intent(this, LoginActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//        }

        Picasso.get().load(BASE_URL_USER + SessionManager.getInstance(this).user.getString("img_profile", "image/profile/default.png"))
            .placeholder(R.drawable.ic_img_profile)
            .transform(CircleTransform())
            .error(R.drawable.ic_img_profile)
            .into(img_profile)

        dotsIndicator = findViewById(R.id.tab_indicator_banner_home)

        val listBanner = ArrayList<Banner>()
        listBanner.add(Banner(R.drawable.ic_img_profile,1,"as","as","-a"))
        listBanner.add(Banner(R.drawable.ic_img_profile,2,"as","as","-a"))
        listBanner.add(Banner(R.drawable.ic_img_profile,3,"as","as","-a"))

        viewPagerBannerHome = findViewById(R.id.viewPagerBannerHome)
        bannerHomeViewPagerAdapter = HomeBannerAdapter(this, listBanner)
        viewPagerBannerHome!!.adapter = bannerHomeViewPagerAdapter

        dotsIndicator.setViewPager(viewPagerBannerHome)

        val handler = Handler()
        val runnable = Runnable {
            if (current_position == listBanner!!.size){
                current_position = 0
            }

            viewPagerBannerHome!!.setCurrentItem(current_position++, true)
        }

        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, 500, 3000)


        val listMenu = ArrayList<Menu>()
        listMenu.add(Menu("Barcode", R.drawable.ic_qr))
        listMenu.add(Menu("Agenda", R.drawable.ic_schedule))
        listMenu.add(Menu("Message", R.drawable.ic_message))
        listMenu.add(Menu("Maps", R.drawable.ic_maps))
        listMenu.add(Menu("Gallery", R.drawable.ic_gallery))
        listMenu.add(Menu("Document", R.drawable.ic_document))
        listMenu.add(Menu("Quiz", R.drawable.ic_quiz))
        listMenu.add(Menu("More", R.drawable.ic_more))
//        listMenu.add(Menu("Souvenir", R.drawable.ic_home_selected))
//        listMenu.add(Menu("Feedback", R.drawable.ic_home_selected))
//        listMenu.add(Menu("Survey", R.drawable.ic_home_selected))
//        listMenu.add(Menu("About", R.drawable.ic_home_selected))
//        listMenu.add(Menu("Help", R.drawable.ic_home_selected))

        val myrv = findViewById<RecyclerView>(R.id.id_recycler_view)
        val myAdapter = MenuAdapter(this, listMenu)
        myrv.layoutManager = GridLayoutManager(this, 4)
        myrv.adapter = myAdapter

    }

}
