package com.example.whatifinished.ui

import com.example.whatifinished.model.ActivityItem

sealed interface MainUiState {
    data object Loading : MainUiState

    data object Empty : MainUiState

    data class Success(val activities: List<ActivityItem>) : MainUiState

    data class Error(val message: String) : MainUiState
}
