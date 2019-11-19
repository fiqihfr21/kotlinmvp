package com.fiqih.event.contract

import com.fiqih.event.model.Event
interface EventContract{

    interface View{
        fun event(event: Event)
    }

    interface Presenter{
        fun getEvent(apptoken:String)
        fun destroyFetch()
    }
}