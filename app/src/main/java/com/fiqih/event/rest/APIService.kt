package com.fiqih.event.rest

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object APIService{

    val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val BASE_URL_STORAGE = "http://mediataklim.tech/"

    fun create() : APIRepository{
        val service  = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return service.create(APIRepository::class.java)
    }
}