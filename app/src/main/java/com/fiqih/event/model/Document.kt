package com.fiqih.event.model

import com.google.gson.annotations.SerializedName

//data class Document(
//    val filename : String,
//    val filesize:String
//)

data class Document(
    @SerializedName("Data")
    val itemDocument: List<itemDocument>,
    val Error: Boolean,
    val Message: String,
    val StatusCode: String
)

data class itemDocument(
    val created_at: String,
    val document_id: Int,
    val event_code: String,
    val event_file: String,
    val file_desc: String,
    val status: String,
    val updated_at: String
)
