package com.example.whatifinished.repository

import com.example.whatifinished.data.ActivityDto
import com.example.whatifinished.model.ActivityItem
import com.example.whatifinished.model.Category
import com.example.whatifinished.network.WhatIFinishedApi
import java.io.IOException
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class ActivityRepositoryImplTest {

    @Test
    fun getActivities_mapsDtosToDomainItems() = runBlocking {
        val repository = ActivityRepositoryImpl(
            api = FakeWhatIFinishedApi(
                activities = listOf(
                    ActivityDto(
                        id = 7L,
                        title = "Dune",
                        category = "books",
                        rating = 5,
                        completionDate = "2026-04-12",
                        notes = null
                    )
                )
            )
        )

        val result = repository.getActivities()

        assertTrue(result is RepositoryResult.Success)
        val activities = (result as RepositoryResult.Success).data
        assertEquals(
            listOf(
                ActivityItem(
                    id = 7L,
                    title = "Dune",
                    category = Category.BOOKS,
                    rating = 5,
                    completionDate = "2026-04-12",
                    notes = ""
                )
            ),
            activities
        )
    }

    @Test
    fun addActivity_returnsSafeErrorWhenNetworkFails() = runBlocking {
        val repository = ActivityRepositoryImpl(
            api = FakeWhatIFinishedApi(
                addActivityError = IOException("offline")
            )
        )

        val result = repository.addActivity(
            ActivityItem(
                title = "Portal 2",
                completionDate = "2026-04-12",
                rating = 5,
                notes = "Replay",
                category = Category.GAMES
            )
        )

        assertTrue(result is RepositoryResult.Error)
        assertEquals(
            "Unable to reach the server. Check your connection and try again.",
            (result as RepositoryResult.Error).message
        )
    }

    @Test
    fun deleteActivity_returnsSafeErrorWhenServerFails() = runBlocking {
        val repository = ActivityRepositoryImpl(
            api = FakeWhatIFinishedApi(
                deleteActivityError = HttpException(
                    Response.error<Any>(
                        500,
                        "{}".toResponseBody("application/json".toMediaType())
                    )
                )
            )
        )

        val result = repository.deleteActivity(9L)

        assertTrue(result is RepositoryResult.Error)
        assertEquals(
            "Server request failed with code 500.",
            (result as RepositoryResult.Error).message
        )
    }

    private class FakeWhatIFinishedApi(
        private val activities: List<ActivityDto> = emptyList(),
        private val addActivityResponse: ActivityDto = ActivityDto(
            id = 1L,
            title = "Default",
            category = "OTHERS",
            rating = null,
            completionDate = "2026-04-12",
            notes = null
        ),
        private val addActivityError: Throwable? = null,
        private val deleteActivityError: Throwable? = null
    ) : WhatIFinishedApi {

        override suspend fun getActivities(): List<ActivityDto> = activities

        override suspend fun addActivity(activity: ActivityDto): ActivityDto {
            addActivityError?.let { throw it }
            return addActivityResponse
        }

        override suspend fun deleteActivity(id: Long) {
            deleteActivityError?.let { throw it }
        }
    }
}
