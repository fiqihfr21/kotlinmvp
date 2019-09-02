package com.fiqih.event.rest

import com.fiqih.event.model.Banner
import com.fiqih.event.model.ScreenItem
import com.fiqih.event.model.UserID
import io.reactivex.Flowable
import retrofit2.http.*
import retrofit2.http.GET

interface APIRepository{

    @GET("splashscreen")
    fun getIntroScreen(): Flowable<ScreenItem>

    @FormUrlEncoded
    @POST("auth/login")
    @Headers("No-Authentication: true")
    fun loginUser(@Field("user_email") email:String, @Field("user_password") password:String): Flowable<UserID>

    @FormUrlEncoded
    @POST("register")
    @Headers("No-Authentication: true")
    fun registerUser(@Field("name") name:String, @Field("user_phone_number") phone:String, @Field("user_email") email:String, @Field("user_password") password:String): Flowable<UserID>

    @GET("posts")
    fun getBanner(@Header("Authorization")auth: String): Flowable<List<Banner>>

}