package com.fiqih.event.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.fiqih.event.R
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import com.fiqih.event.contract.HomeBannerContract
import com.fiqih.event.model.Banner
import com.fiqih.event.model.ScreenItem
import com.fiqih.event.presenter.APIRepositoryImplement
import com.fiqih.event.presenter.HomeBannerPresenter
import com.fiqih.event.rest.APIService
import com.fiqih.event.util.SessionManager
import com.fiqih.event.view.adapter.HomeBannerAdapter
import com.fiqih.event.view.adapter.IntroScreenAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList

class IntroActivity : AppCompatActivity(), HomeBannerContract.View {

    private var viewPagerIntro: ViewPager? = null
    internal lateinit var introViewPagerAdapter: IntroScreenAdapter
    internal lateinit var tabLayoutIndicator: TabLayout
    internal lateinit var btnNext: Button
    internal var position = 0
    internal lateinit var btnGetStarted: Button
    internal lateinit var btnAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //cek udah pernah buka aplikasinya atau belum
//        if (restorePrefData()) {
//            val intentMyActivity = Intent(applicationContext, MainActivity::class.java)
//            startActivity(intentMyActivity)
//            finish()
//        }
        doRequest()

        setContentView(R.layout.activity_intro)

    }

    private lateinit var presenter : HomeBannerPresenter

    private fun doRequest(){
        presenter = HomeBannerPresenter(this, APIRepositoryImplement(APIService.create()))
        presenter.getBanner("Bearer " + SessionManager.getInstance(this).user.token)
    }

    override fun showLoading() {

    }

    override fun listProfile(banner: List<Banner>) {
        Log.i("data banner", ": $banner")
        btnNext = findViewById(R.id.btn_next)
        tabLayoutIndicator = findViewById(R.id.tab_indicator_intro)
        btnGetStarted = findViewById(R.id.btn_get_started)
        btnAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.button_animation)

        // fill list screen
//        val mList = ArrayList<ScreenItem>()
//        mList.add(
//            ScreenItem(
//                "Scan",
//                "Lorem ipsum dolor sit amet, eu nihil prompta vix, ne tacimates imperdiet vel, eu vis simul veniam regione. Postulant efficiendi sed at, eius liber bonorum id quo, mei ad noluisse patrioque. ",
//                R.drawable.scan
//            )
//        )
//        mList.add(
//            ScreenItem(
//                "Search",
//                "Lorem ipsum dolor sit amet, eu nihil prompta vix, ne tacimates imperdiet vel, eu vis simul veniam regione. Postulant efficiendi sed at, eius liber bonorum id quo, mei ad noluisse patrioque.",
//                R.drawable.search
//            )
//        )
//        mList.add(
//            ScreenItem(
//                "Navigate",
//                "Lorem ipsum dolor sit amet, eu nihil prompta vix, ne tacimates imperdiet vel, eu vis simul veniam regione. Postulant efficiendi sed at, eius liber bonorum id quo, mei ad noluisse patrioque.",
//                R.drawable.navigate
//            )
//        )

        // setup viewpager
        viewPagerIntro = findViewById(R.id.viewPagerIntroSlider)
        introViewPagerAdapter = IntroScreenAdapter(this, banner)
        viewPagerIntro!!.adapter = introViewPagerAdapter

        // setup tab layout with view pager
        tabLayoutIndicator.setupWithViewPager(viewPagerIntro)

        // btn next set on click listener
        btnNext.setOnClickListener {
            position = viewPagerIntro!!.currentItem
            if (position < banner.size) {
                position++
                viewPagerIntro!!.currentItem = position
            }

            if (position == banner.size - 1) {
                loadLastScreen()
            }
        }

        // tab layout add change listener
        tabLayoutIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == banner.size - 1) {
                    loadLastScreen()
                } else if (tab.position != banner.size - 1) {
                    reloadScreen()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        // btn get started set on click listener
        btnGetStarted.setOnClickListener {
            val intentMyActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(intentMyActivity)

            // buat user ga akses intro lagi kalo udah pernah buka
            // pake shared pref
            savePrefsData()
            finish()
        }
    }

    override fun hideLoading() {

    }

    private fun partItemClicked(partItem: Banner) {

    }

    private fun restorePrefData(): Boolean {
        val pref = applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        return pref.getBoolean("isIntroOpened", false)

    }

    private fun savePrefsData() {
        val pref = applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("isIntroOpened", true)
        editor.commit()
    }

    private fun loadLastScreen() {
        btnGetStarted.visibility = View.VISIBLE
        tabLayoutIndicator.visibility = View.VISIBLE
        btnNext.visibility = View.INVISIBLE

        // button animation
        btnGetStarted.animation = btnAnimation

    }

    private fun reloadScreen() {
        btnGetStarted.visibility = View.INVISIBLE
        tabLayoutIndicator.visibility = View.VISIBLE
        btnNext.visibility = View.VISIBLE
    }
}
