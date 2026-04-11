package com.example.whatifinished.network

import com.example.whatifinished.model.ActivityItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WhatIFinishedApiService {
    @GET("/api/activities")
    suspend fun getActivities(): List<ActivityItem>

    @POST("/api/activities")
    suspend fun addActivity(@Body activity: ActivityItem): ActivityItem

    @DELETE("/api/activities/{id}")
    suspend fun deleteActivity(@Path("id") id: Long)
}
