package com.fiqih.event.presenter

import com.fiqih.event.contract.APIRepositoryContract
import com.fiqih.event.model.Banner
import com.fiqih.event.model.LogRegAPIResponse
import com.fiqih.event.model.ScreenItem
import com.fiqih.event.model.UserID
import com.fiqih.event.rest.APIRepository
import io.reactivex.Flowable
import retrofit2.Response

class APIRepositoryImplement(private val apiRepository: APIRepository): APIRepositoryContract{

    override fun getIntroScreen(): Flowable<ScreenItem> {
        return apiRepository.getIntroScreen()
    }

    override fun login(email:String, password:String): Flowable<UserID> {
        return apiRepository.loginUser(email,password)
    }

    override fun register(name: String, phone: String, email: String, password: String): Flowable<UserID> {
        return apiRepository.registerUser(name,phone,email,password)
    }

    override fun getBanner(auth:String): Flowable<List<Banner>> {
        return apiRepository.getBanner(auth)
    }

}