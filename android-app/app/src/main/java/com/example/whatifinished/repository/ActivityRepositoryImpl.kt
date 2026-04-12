package com.example.whatifinished.repository

import com.example.whatifinished.model.ActivityItem
import com.example.whatifinished.model.toDomainModel
import com.example.whatifinished.model.toDto
import com.example.whatifinished.network.WhatIFinishedApi
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException

class ActivityRepositoryImpl @Inject constructor(
    private val api: WhatIFinishedApi
) : ActivityRepository {

    override suspend fun getActivities(): RepositoryResult<List<ActivityItem>> {
        return safeApiCall {
            api.getActivities().map { dto -> dto.toDomainModel() }
        }
    }

    override suspend fun addActivity(activity: ActivityItem): RepositoryResult<ActivityItem> {
        return safeApiCall {
            api.addActivity(activity.toDto()).toDomainModel()
        }
    }

    override suspend fun deleteActivity(id: Long): RepositoryResult<Unit> {
        return safeApiCall {
            api.deleteActivity(id)
        }
    }

    private suspend fun <T> safeApiCall(block: suspend () -> T): RepositoryResult<T> {
        return try {
            RepositoryResult.Success(block())
        } catch (exception: IOException) {
            RepositoryResult.Error(
                message = "Unable to reach the server. Check your connection and try again.",
                cause = exception
            )
        } catch (exception: HttpException) {
            RepositoryResult.Error(
                message = "Server request failed with code ${exception.code()}.",
                cause = exception
            )
        } catch (exception: Exception) {
            RepositoryResult.Error(
                message = "Unexpected error while loading activities.",
                cause = exception
            )
        }
    }
}
