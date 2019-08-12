package com.fiqih.event.contract

import com.fiqih.event.model.Banner

interface HomeBannerContract{

    interface View{
        fun showLoading()
        fun listProfile(banner: List<Banner>)
        fun hideLoading()
    }

    interface Presenter{
        fun getBanner(auth:String)
        fun destroyFetch()
    }
}