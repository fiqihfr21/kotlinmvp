package com.fiqih.event.contract

import com.fiqih.event.model.AppKey

interface SplaschScreenContract{

    interface View{
        fun logRegResponse(logRegResponse: AppKey)
    }

    interface Presenter{
        fun getToken(apptoken :String, securitykey:String)
    }
}