package com.fiqih.event.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("Data")
    val eventDetail: EventDetail,
    val Error: Boolean,
    val Message: String,
    val StatusCode: String
)

data class EventDetail(
    val background_color: String,
    val created_at: String,
    val event_category: String,
    val event_code: String,
    val event_desc: String,
    val event_duration: String,
    val event_end: String,
    val event_id: Int,
    val event_image: String,
    val event_latitude: String,
    val event_location: String,
    val event_longitude: String,
    val event_start: String,
    val event_title: String,
    val icon_color: String,
    val status: String,
    val updated_at: String
)