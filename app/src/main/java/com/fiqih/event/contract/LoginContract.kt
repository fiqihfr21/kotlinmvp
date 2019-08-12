package com.fiqih.event.contract

import com.fiqih.event.model.LogRegAPIResponse

interface LoginContract{

    interface View{
        fun showLoading()
        fun logRegResponse(logRegResponse: LogRegAPIResponse)
        fun hideLoading()
    }

    interface Presenter{
        fun getUser(email:String, password:String)
        fun destroyFetch()
    }
}