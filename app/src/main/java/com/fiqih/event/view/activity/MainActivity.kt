package com.fiqih.event.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.fiqih.event.R
import com.fiqih.event.util.SessionManager
import com.fiqih.event.util.TabEntity
import com.fiqih.event.view.fragment.*
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val mTitles = arrayOf("Home", "Event", "Tiket", "Message", "Profile")

    private val mIconUnSelectIds = intArrayOf(R.drawable.ic_home_unselected, R.drawable.ic_home_unselected, R.drawable.ic_home_unselected, R.drawable.ic_home_unselected,R.drawable.ic_home_unselected)
    private val mIconSelectIds = intArrayOf(R.drawable.ic_home_selected,  R.drawable.ic_home_selected, R.drawable.ic_home_selected, R.drawable.ic_home_selected,R.drawable.ic_home_selected)

    private val mTabEntities = ArrayList<CustomTabEntity>()
    private var mIndex = 0

    private var homeFragment: HomeFragment? = null
    private var profileFragment: ProfileFragment? = null
    private var eventFragment: EventFragment? = null
    private var tiketFragment: TiketFragment? = null
    private var messageFragment: MessageFragment? = null

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

        initTab()
        bttm_nav.currentTab = mIndex
        switchFragment(mIndex)
    }

    private fun initTab() {
        (0 until mTitles.size)
            .mapTo(mTabEntities) {
                TabEntity(
                    mTitles[it],
                    mIconSelectIds[it],
                    mIconUnSelectIds[it]
                )
            }

        bttm_nav.setTabData(mTabEntities)
        bttm_nav.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0
            -> homeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance(mTitles[position]).let {
                homeFragment = it
                transaction.add(R.id.fl_container, it, "")
            }
            1
            ->eventFragment?.let {
                transaction.show(it)
            } ?: EventFragment.getInstance(mTitles[position]).let {
                eventFragment = it
                transaction.add(R.id.fl_container, it, "") }
            2
            -> tiketFragment?.let {
                transaction.show(it)
            } ?: TiketFragment.getInstance(mTitles[position]).let {
                tiketFragment = it
                transaction.add(R.id.fl_container, it, "") }

            3
            -> messageFragment?.let {
                transaction.show(it)
            } ?: MessageFragment.getInstance(mTitles[position]).let {
                messageFragment = it
                transaction.add(R.id.fl_container, it, "") }
            4
            -> profileFragment?.let {
                transaction.show(it)
            } ?: ProfileFragment.getInstance(mTitles[position]).let {
                profileFragment = it
                transaction.add(R.id.fl_container, it, "") }
            else
            -> homeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance(mTitles[position]).let {
                homeFragment = it
                transaction.add(R.id.fl_container, it, "") }
        }

        mIndex = position
        bttm_nav.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        homeFragment?.let { transaction.hide(it) }
        profileFragment?.let { transaction.hide(it) }
        eventFragment?.let { transaction.hide(it) }
        tiketFragment?.let { transaction.hide(it) }
        messageFragment?.let { transaction.hide(it) }
    }

}
