package com.fiqih.event.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fiqih.event.R
import kotlinx.android.synthetic.main.activity_schedule.*
import android.support.design.widget.TabLayout
import com.fiqih.event.view.adapter.ScheduleAdapter


class ScheduleActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        im_back.setOnClickListener {
            finish()
        }

        for (k in 14..15) {
            tabs.addTab(tabs.newTab())
        }

        val adapter = ScheduleAdapter(supportFragmentManager, tabs.getTabCount())
        frameLayout.setAdapter(adapter)
        frameLayout.setOffscreenPageLimit(1)
        frameLayout.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
//        if (tabs.getTabCount() === 2) {
//            tabs.setTabMode(TabLayout.MODE_FIXED)
//        } else {
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE)
//        }
        tabs.setupWithViewPager(frameLayout)
    }
}