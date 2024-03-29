package com.fiqih.event.rest

import com.fiqih.event.model.*
import io.reactivex.Flowable
import retrofit2.http.*
import retrofit2.http.GET

interface APIRepository{

    @POST("auth/public/api/token")
    fun getAppToken(@Header("apptoken")apptoken : String,@Header("securitykey")securitykey : String):Flowable<AppKey>

    @GET("gateway/public/splashscreen")
    fun getIntroScreen(@Header("token")apptoken : String): Flowable<ScreenItem>

    @FormUrlEncoded
    @POST("member/public/auth/login")
    @Headers("No-Authentication: true")
    fun loginUser(@Field("user_email") email:String, @Field("user_password") password:String): Flowable<UserID>

    @FormUrlEncoded
    @POST("register")
    @Headers("No-Authentication: true")
    fun registerUser(@Field("name") name:String, @Field("user_phone_number") phone:String, @Field("user_email") email:String, @Field("user_password") password:String): Flowable<UserID>

    @GET("gateway/public/banner")
    fun getBanner(@Header("token")apptoken: String): Flowable<Banner>

    @GET("gateway/public/document")
    fun getDocument(@Header("token")apptoken: String) : Flowable<Document>

    @GET("gateway/public/gallery")
    fun getGalery(@Header("token")apptoken: String):Flowable<Galery>

    @GET("gateway/public/viewevent")
    fun getEvent(@Header("token")apptoken: String):Flowable<Event>

    @GET ("product/public/viewquiz")
    fun getQuiz(@Header("token")apptoken: String):Flowable<Quiz>

    @GET("product/public/question")
    fun getQuestion(@Header("token")apptoken: String):Flowable<Quiz>


}