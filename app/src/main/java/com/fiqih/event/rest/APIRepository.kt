package com.fiqih.event.rest

import com.fiqih.event.model.Banner
import com.fiqih.event.model.LogRegAPIResponse
import io.reactivex.Flowable
import retrofit2.http.*

interface APIRepository{

    @FormUrlEncoded
    @POST("login")
    @Headers("No-Authentication: true")
    fun loginUser(@Field("email") email:String, @Field("password") password:String): Flowable<LogRegAPIResponse>

    @GET("posts")
    fun getBanner(@Header("Authorization")auth: String): Flowable<List<Banner>>

}