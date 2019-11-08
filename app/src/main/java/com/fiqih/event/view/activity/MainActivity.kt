package com.fiqih.event.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fiqih.event.R
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
import android.util.Log
import android.view.View
import com.fiqih.event.contract.HomeBannerContract
import com.fiqih.event.model.Menu
import com.fiqih.event.model.itemBanner
import com.fiqih.event.presenter.APIRepositoryImplement
import com.fiqih.event.presenter.HomeBannerPresenter
import com.fiqih.event.presenter.IntroScreenPresenter
import com.fiqih.event.rest.APIService
import com.fiqih.event.rest.APIService.BASE_URL
import com.fiqih.event.view.adapter.MenuAdapter
import kotlinx.android.synthetic.main.bottom_sheet_grid.*


class MainActivity : AppCompatActivity(), HomeBannerContract.View {

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

        doRequestBanner()

        Picasso.get().load(BASE_URL + SessionManager.getInstance(this).user.getString("img_profile", "image/profile/default.png"))
            .placeholder(R.drawable.ic_img_profile)
            .transform(CircleTransform())
            .error(R.drawable.ic_img_profile)
            .into(img_profile)

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


    private lateinit var homeBannerPresenter : HomeBannerPresenter
    private fun doRequestBanner(){
        homeBannerPresenter = HomeBannerPresenter(this@MainActivity, APIRepositoryImplement(APIService.Api()))
        homeBannerPresenter.getBanner(SessionManager.getInstance(this).apptoken.getString("apptoken", "default_app_token"))
    }

    override fun listProfile(banner: Banner) {

        Log.i("tes_app_banner", banner.toString())
        dotsIndicator = findViewById(R.id.tab_indicator_banner_home)

        viewPagerBannerHome = findViewById(R.id.viewPagerBannerHome)
        bannerHomeViewPagerAdapter = HomeBannerAdapter(this, banner.itemBanner)
        viewPagerBannerHome!!.adapter = bannerHomeViewPagerAdapter

        dotsIndicator.setViewPager(viewPagerBannerHome)

        val handler = Handler()
        val runnable = Runnable {
            if (current_position == banner.itemBanner!!.size){
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
    }

}
