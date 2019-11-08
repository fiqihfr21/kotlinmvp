package com.fiqih.event.model

import com.google.gson.annotations.SerializedName

//class ScreenItem(
//    var title: String,
//    val body:String,
//    val image:String
//)

data class AppKey(
    val StatusCode : Int,
    val Error : String,
    val Message: String,
    val Data : String
)

data class ScreenItem(
    @SerializedName("Data")
    val itemScreen: List<itemScreen>,
    val Error: Boolean,
    val Message: String
)

data class itemScreen(
//    val Activation: String,
//    val created_at: String,
//    val deleted_at: Any,
//    val event_code: String,
    var id: Int,
    var splash_bg_color: String,
    var splash_desc: String,
    var splash_image: String,
    var splash_title: String
//    val updated_at: String
)