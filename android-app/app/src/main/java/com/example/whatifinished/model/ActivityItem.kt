package com.example.whatifinished.model

import com.google.gson.annotations.SerializedName

data class ActivityItem(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("title")
    val title: String,
    @SerializedName("category")
    val category: Category,
    @SerializedName("rating")
    val rating: Int? = null,
    @SerializedName("completionDate")
    val completionDate: String,
    @SerializedName("notes")
    val notes: String? = null
)