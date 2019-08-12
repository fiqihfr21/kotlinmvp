package com.fiqih.event.contract

import com.fiqih.event.model.Banner
import com.fiqih.event.model.LogRegAPIResponse
import io.reactivex.Flowable

interface APIRepositoryContract{

    fun login(email:String, password:String):Flowable<LogRegAPIResponse>

    fun getBanner(auth: String):Flowable<List<Banner>>
}