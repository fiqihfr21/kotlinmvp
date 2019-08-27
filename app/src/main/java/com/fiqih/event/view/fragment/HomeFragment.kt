package com.fiqih.event.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fiqih.event.R
import com.fiqih.event.contract.HomeBannerContract
import com.fiqih.event.model.Banner
import com.fiqih.event.presenter.APIRepositoryImplement
import com.fiqih.event.presenter.HomeBannerPresenter
import com.fiqih.event.rest.APIService
import com.fiqih.event.util.SessionManager
import com.fiqih.event.view.adapter.HomeBannerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeBannerContract.View {

    private var mTitle:String? = null

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //doRequest()
    }

    private lateinit var presenter : HomeBannerPresenter

    private fun doRequest(){
        presenter = HomeBannerPresenter(this, APIRepositoryImplement(APIService.ApiUser()))
        presenter.getBanner("Bearer " + SessionManager.getInstance(this!!.context!!).user.token)
    }

    override fun showLoading() {

    }

    override fun listProfile(banner: List<Banner>) {
        Log.i("data banner", ": $banner")
        rv_banner.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = HomeBannerAdapter(banner, { partItem: Banner -> partItemClicked(partItem) })
        }
    }

    override fun hideLoading() {

    }

    private fun partItemClicked(partItem: Banner) {
        Toast.makeText(activity, "ini adalah : ${partItem.title}", Toast.LENGTH_SHORT).show()
    }

}
