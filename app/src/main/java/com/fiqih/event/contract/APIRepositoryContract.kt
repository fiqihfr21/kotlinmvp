package com.fiqih.event.contract

import com.fiqih.event.model.Banner
import com.fiqih.event.model.ScreenItem
import com.fiqih.event.model.UserID
import io.reactivex.Flowable

interface APIRepositoryContract{

    fun getIntroScreen():Flowable<ScreenItem>

    fun login(email:String, password:String):Flowable<UserID>

    fun register(name:String, phone:String,email:String, password:String):Flowable<UserID>

    fun getBanner(auth: String):Flowable<List<Banner>>
}