package com.example.whatifinished.repository

import com.example.whatifinished.model.ActivityItem
import com.example.whatifinished.model.toDomainModel
import com.example.whatifinished.model.toDto
import com.example.whatifinished.network.WhatIFinishedApiService
import javax.inject.Inject

class ActivityRepository @Inject constructor(
    private val api: WhatIFinishedApiService
) {
    suspend fun getActivities(): List<ActivityItem> {
        return api.getActivities().map { it.toDomainModel() }
    }

    suspend fun addActivity(activity: ActivityItem) : ActivityItem {
        val dtoToSend = activity.toDto()
        val responseDto = api.addActivity(dtoToSend)
        return responseDto.toDomainModel()
    }

    suspend fun deleteActivity(id: Long) {
        api.deleteActivity(id)
    }
}