package com.fiqih.event.contract

import com.fiqih.event.model.Galery

interface GaleryContract{

    interface View{
        fun listGalery(galery: Galery)
    }

    interface Presenter{
        fun getGalery(apptoken:String)
        fun destroyFetch()
    }
}