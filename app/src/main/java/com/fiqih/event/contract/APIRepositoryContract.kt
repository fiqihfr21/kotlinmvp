package com.fiqih.event.contract

import com.fiqih.event.model.*
import io.reactivex.Flowable

interface APIRepositoryContract{

    fun getAppToken(apptoken :String, securitykey:String) : Flowable<AppKey>

    fun getIntroScreen(apptoken: String):Flowable<ScreenItem>

    fun login(email:String, password:String):Flowable<UserID>

    fun register(name:String, phone:String,email:String, password:String):Flowable<UserID>

    fun getBanner(auth: String):Flowable<Banner>

    fun getDocument(auth: String):Flowable<Document>

    fun getGalery(auth: String):Flowable<Galery>

    fun getEvent(auth: String):Flowable<Event>
}