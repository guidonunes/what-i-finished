package com.example.whatifinished.network

import com.example.whatifinished.data.ActivityDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WhatIFinishedApiService {
    @GET("/api/activities")
    suspend fun getActivities(): List<ActivityDtoDto>

    @POST("/api/activities")
    suspend fun addActivity(@Body activity: ActivityDto): ActivityDto

    @DELETE("/api/activities/{id}")
    suspend fun deleteActivity(@Path("id") id: Long)
}


