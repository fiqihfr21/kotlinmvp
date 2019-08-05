package com.fiqih.event.presenter

import com.fiqih.event.contract.APIRepositoryContract
import com.fiqih.event.model.Profile
import com.fiqih.event.rest.APIRepository
import io.reactivex.Flowable

class APIRepositoryImplement(private val apiRepository: APIRepository): APIRepositoryContract{

    override fun getProfile(): Flowable<List<Profile>> {
        return apiRepository.getProfile()
    }

}