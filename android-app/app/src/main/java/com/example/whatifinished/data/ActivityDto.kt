package com.example.whatifinished.data

import com.google.gson.annotations.SerializedName

data class ActivityDto(
    @SerializedName("id")
    val id: Long? = null

    @SerializedName("title")
    val title: String,

    @SerializedName("completionDate")
    val completionDate: String,

    @SerializedName("rating")
    val rating: Int,

    @SerializedName("notes")
    val notes: String,

    @SerializedName("category")
    val category: String
)
