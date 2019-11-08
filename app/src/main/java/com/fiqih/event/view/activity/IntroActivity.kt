package com.fiqih.event.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.fiqih.event.R
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import com.fiqih.event.contract.HomeBannerContract
import com.fiqih.event.contract.IntroScreenContract
import com.fiqih.event.model.Banner
import com.fiqih.event.model.ScreenItem
import com.fiqih.event.presenter.APIRepositoryImplement
import com.fiqih.event.presenter.HomeBannerPresenter
import com.fiqih.event.presenter.IntroScreenPresenter
import com.fiqih.event.rest.APIService
import com.fiqih.event.util.SessionManager
import com.fiqih.event.view.adapter.IntroScreenAdapter
import kotlinx.android.synthetic.main.activity_intro.*
import retrofit2.Response

class IntroActivity : AppCompatActivity(), IntroScreenContract.View {

    private var viewPagerIntro: ViewPager? = null
    internal lateinit var introViewPagerAdapter: IntroScreenAdapter
    internal lateinit var tabLayoutIndicator: TabLayout
    internal lateinit var btnNext: Button
    internal lateinit var btnSkip: Button
    internal var position = 0
    internal lateinit var btnGetStarted: Button
    internal lateinit var btnAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        //cek udah pernah buka aplikasinya atau belum
        if (SessionManager.getInstance(this).isFirstTimeLaunch) {
            val intentMyActivity = Intent(this, LoginActivity::class.java)
            startActivity(intentMyActivity)
            finish()
        }
        doRequest()
    }

    private lateinit var presenter : IntroScreenPresenter

    private fun doRequest(){
        presenter = IntroScreenPresenter(this, APIRepositoryImplement(APIService.Api()))
        presenter.getIntroScreen(SessionManager.getInstance(this).apptoken.getString("apptoken", "default_app_token"))
    }

    override fun listIntro(screenItem: ScreenItem) {

        btnNext = findViewById(R.id.btn_next)
        btnSkip = findViewById(R.id.btnskip)
        tabLayoutIndicator = findViewById(R.id.tab_indicator_intro)
        btnGetStarted = findViewById(R.id.btn_get_started)
        btnAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.button_animation)

        // setup viewpager
        viewPagerIntro = findViewById(R.id.viewPagerIntroSlider)
        introViewPagerAdapter = IntroScreenAdapter(this, screenItem.itemScreen)
        viewPagerIntro!!.adapter = introViewPagerAdapter

        // setup tab layout with view pager
        tabLayoutIndicator.setupWithViewPager(viewPagerIntro)

        // btn next set on click listener
        btnNext.setOnClickListener {
            position = viewPagerIntro!!.currentItem
            if (position < screenItem.itemScreen.size) {
                position++
                viewPagerIntro!!.currentItem = position
            }

            if (position == screenItem.itemScreen.size - 1) {
                loadLastScreen()
            }
        }

        btnskip.setOnClickListener {
            val intentMyActivity = Intent(this, LoginActivity::class.java)
            startActivity(intentMyActivity)
            SessionManager.getInstance(this).setFirstTimeLaunch(true)
            finish()
        }

        // tab layout add change listener
        tabLayoutIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == screenItem.itemScreen.size - 1) {
                    loadLastScreen()
                } else if (tab.position != screenItem.itemScreen.size - 1) {
                    reloadScreen()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        btnGetStarted.setOnClickListener {
            val intentMyActivity = Intent(this, LoginActivity::class.java)
            startActivity(intentMyActivity)
            SessionManager.getInstance(this).setFirstTimeLaunch(true)
            finish()
        }
    }


    private fun loadLastScreen() {
        btnGetStarted.visibility = View.VISIBLE
        tabLayoutIndicator.visibility = View.VISIBLE
        btnNext.visibility = View.INVISIBLE
        btnSkip.visibility = View.INVISIBLE

        btnGetStarted.animation = btnAnimation

    }

    private fun reloadScreen() {
        btnGetStarted.visibility = View.INVISIBLE
        tabLayoutIndicator.visibility = View.VISIBLE
        btnNext.visibility = View.VISIBLE
        btnSkip.visibility = View.VISIBLE
    }
}
