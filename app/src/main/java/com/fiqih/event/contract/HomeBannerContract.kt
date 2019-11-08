package com.fiqih.event.contract

import com.fiqih.event.model.Banner
import com.fiqih.event.model.itemBanner

interface HomeBannerContract{

    interface View{
//        fun showLoading()
        fun listProfile(banner: Banner)
//        fun hideLoading()
    }

    interface Presenter{
        fun getBanner(apptoken:String)
        fun destroyFetch()
    }
}