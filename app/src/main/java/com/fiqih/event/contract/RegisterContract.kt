package com.fiqih.event.contract

import com.fiqih.event.model.UserID

interface RegisterContract{

    interface View{
        fun showLoading()
        fun logRegResponse(logRegResponse: UserID)
        fun hideLoading()
    }

    interface Presenter{
        fun getUser(name:String, phone:String, email:String, password:String)
        fun destroyFetch()
    }
}