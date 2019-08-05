package com.fiqih.event.contract

import com.fiqih.event.model.Profile
import io.reactivex.Flowable

interface APIRepositoryContract{

    fun getProfile():Flowable<List<Profile>>
}