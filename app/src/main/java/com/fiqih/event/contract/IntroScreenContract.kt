package com.fiqih.event.contract

import com.fiqih.event.model.Banner

interface IntroScreenContract{

    interface View{
        fun listIntro(banner: List<Banner>)
    }

    interface Presenter{
        fun getBanner(auth:String)
        fun destroyFetch()
    }
}