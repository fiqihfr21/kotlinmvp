package com.fiqih.event.contract

import com.fiqih.event.model.Profile

interface MainContract{

    interface View{
        fun showLoading()
        fun listProfile(profile: List<Profile>)
        fun hideLoading()
    }

    interface Presenter{
        fun getProfile()
        fun destroyFetch()
    }
}