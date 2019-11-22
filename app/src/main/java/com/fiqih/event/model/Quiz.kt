package com.fiqih.event.model

import com.google.gson.annotations.SerializedName

data class Quiz(
    @SerializedName("Data")
    val itemQuiz: List<itemQuiz>,
    val Error: Boolean,
    val Message: String
)

data class itemQuiz(
    val created_at: String,
    val updated_at: String,
    val event_code: String,
    val quiz_code: String,
    val quiz_id: Int,
    val name: String,
    val jumlah_point: Int,
    val total_waktu: Int,
    val status: String
)