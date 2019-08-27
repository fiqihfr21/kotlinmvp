package com.fiqih.event.rest

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object APIService{

    val BASE_URL_GENERAL = "http://104.248.149.236/general/public/"
    val BASE_URL_USER = "http://104.248.149.236/user/public/"

    fun ApiGeneral() : APIRepository{
        val service  = Retrofit.Builder()
            .baseUrl(BASE_URL_GENERAL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return service.create(APIRepository::class.java)
    }

    fun ApiUser() : APIRepository{
        val service  = Retrofit.Builder()
            .baseUrl(BASE_URL_USER)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return service.create(APIRepository::class.java)
    }
}