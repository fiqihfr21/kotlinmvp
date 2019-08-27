package com.fiqih.event.model

data class LogRegAPIResponse(
    val error: String,
    val user: UserID
)

data class UserID(
    val id:Int,
    val token: String,
    val status_code: Int,
    val error: String
)

data class User(
    val id: Int,
    val email: String,
    val email_verified_at: Any,
    val name: String,
    val avatar: String
)