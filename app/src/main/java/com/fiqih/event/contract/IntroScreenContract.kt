package com.fiqih.event.contract

import com.fiqih.event.model.ScreenItem

interface IntroScreenContract{

    interface View{
        fun listIntro(screenItem: ScreenItem)
    }

    interface Presenter{
        fun getIntroScreen(apptoken : String)
        fun destroyFetch()
    }
}