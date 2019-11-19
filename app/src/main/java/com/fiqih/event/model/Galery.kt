package com.fiqih.event.model

import com.google.gson.annotations.SerializedName

//data class Galery(
//    val image:String
//)

data class Galery(
    @SerializedName("Data")
    val itemGalery: List<itemGalery>,
    val Error: Boolean,
    val Message: String,
    val StatusCode: String
)

data class itemGalery(
    val created_at: String,
    val event_code: String,
    val galery_desc: String,
    val galery_id: Int,
    val galery_image: String,
    val galery_title: String,
    val status: String,
    val updated_at: String
)