package com.fiqih.event.model

import com.google.gson.annotations.SerializedName

data class UserID(
    val Token: String,
    val StatusCode: Int,
    val Error: String,
    @SerializedName("Data") val user:User
)

data class User(
    val id: Int,
    val activation_code: Any,
    val active: String,
    val created_at: String,
    val job_title: Any,
    val name: String,
    val organisasi: Any,
    val remember_token: Any,
    val updated_at: String,
    val user_email: String,
    val user_image: String,
    val user_password: String,
    val user_phone_number: String
)

