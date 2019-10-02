package com.fiqih.event.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.fiqih.event.view.fragment.ScheduleFragment


class ScheduleAdapter(fm: FragmentManager, internal var mNumOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return ScheduleFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return position.toString()
    }

}