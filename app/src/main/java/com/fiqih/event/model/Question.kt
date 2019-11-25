package com.fiqih.event.model

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("Data")
    val itemQuestion: List<itemQuestion>,
    val Error: Boolean,
    val Message: String
)

data class itemQuestion(
    val created_at: String,
    val updated_at: String,
    val status: String,
    val question_id: Int,
    val quiz_code: String,
    val pertanyaan: String,
    val jawaban_1: String,
    val jawaban_2: String,
    val jawaban_3: String,
    val jawaban_4: String,
    val jawaban_benar: String,
    val point: Int,
    val time: Int
)