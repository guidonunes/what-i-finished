package com.example.whatifinished.repository

sealed interface RepositoryResult<out T> {
    data class Success<T>(val data: T) : RepositoryResult<T>

    data class Error(
        val message: String,
        val cause: Throwable? = null
    ) : RepositoryResult<Nothing>
}
