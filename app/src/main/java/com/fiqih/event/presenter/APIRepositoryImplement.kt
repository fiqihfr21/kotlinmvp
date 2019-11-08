package com.fiqih.event.presenter

import com.fiqih.event.contract.APIRepositoryContract
import com.fiqih.event.model.AppKey
import com.fiqih.event.model.Banner
import com.fiqih.event.model.ScreenItem
import com.fiqih.event.model.UserID
import com.fiqih.event.rest.APIRepository
import io.reactivex.Flowable

class APIRepositoryImplement(private val apiRepository: APIRepository): APIRepositoryContract{

    override fun getAppToken(apptoken : String, securitykey:String): Flowable<AppKey> {
        return  apiRepository.getAppToken(apptoken, securitykey)
    }

    override fun getIntroScreen(apptoken: String): Flowable<ScreenItem> {
        return apiRepository.getIntroScreen(apptoken)
    }

    override fun login(email:String, password:String): Flowable<UserID> {
        return apiRepository.loginUser(email,password)
    }

    override fun register(name: String, phone: String, email: String, password: String): Flowable<UserID> {
        return apiRepository.registerUser(name,phone,email,password)
    }

    override fun getBanner(auth:String): Flowable<Banner> {
        return apiRepository.getBanner(auth)
    }

}