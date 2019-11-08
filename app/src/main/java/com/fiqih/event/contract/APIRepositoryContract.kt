package com.fiqih.event.contract

import com.fiqih.event.model.AppKey
import com.fiqih.event.model.Banner
import com.fiqih.event.model.ScreenItem
import com.fiqih.event.model.UserID
import io.reactivex.Flowable

interface APIRepositoryContract{

    fun getAppToken(apptoken :String, securitykey:String) : Flowable<AppKey>

    fun getIntroScreen(apptoken: String):Flowable<ScreenItem>

    fun login(email:String, password:String):Flowable<UserID>

    fun register(name:String, phone:String,email:String, password:String):Flowable<UserID>

    fun getBanner(auth: String):Flowable<Banner>
}