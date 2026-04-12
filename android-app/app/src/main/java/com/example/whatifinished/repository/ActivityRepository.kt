package com.example.whatifinished.repository

import com.example.whatifinished.model.ActivityItem

interface ActivityRepository {
    suspend fun getActivities(): RepositoryResult<List<ActivityItem>>

    suspend fun addActivity(activity: ActivityItem): RepositoryResult<ActivityItem>

    suspend fun deleteActivity(id: Long): RepositoryResult<Unit>
}
