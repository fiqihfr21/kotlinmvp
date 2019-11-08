package com.fiqih.event.model

import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("Data")
    val itemBanner: List<itemBanner>,
    val Error: Boolean,
    val Message: String,
    val StatusCode: String
)

data class itemBanner(
    val banner_desc: String,
    val banner_id: Int,
    val banner_image: String,
    val banner_name: String,
    val created_at: String,
    val deleted_at: Any,
    val event_code: String,
    val status: String,
    val updated_at: String
)