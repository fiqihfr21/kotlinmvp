package com.fiqih.event.contract

import com.fiqih.event.model.UserID

interface LoginContract{

    interface View{
        fun showLoading()
        fun logRegResponse(logRegResponse: UserID)
        fun hideLoading()
    }

    interface Presenter{
        fun getUser(email:String, password:String)
        fun destroyFetch()
    }
}