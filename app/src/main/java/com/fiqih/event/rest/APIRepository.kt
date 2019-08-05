package com.fiqih.event.rest

import com.fiqih.event.model.Profile
import io.reactivex.Flowable
import retrofit2.http.GET

interface APIRepository{

    @GET("users")
    fun getProfile(): Flowable<List<Profile>>
}