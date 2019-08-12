package com.fiqih.event.presenter

import com.fiqih.event.contract.APIRepositoryContract
import com.fiqih.event.model.Banner
import com.fiqih.event.model.LogRegAPIResponse
import com.fiqih.event.rest.APIRepository
import io.reactivex.Flowable

class APIRepositoryImplement(private val apiRepository: APIRepository): APIRepositoryContract{

    override fun login(email:String, password:String): Flowable<LogRegAPIResponse> {
        return apiRepository.loginUser(email,password)
    }

    override fun getBanner(auth:String): Flowable<List<Banner>> {
        return apiRepository.getBanner(auth)
    }

}