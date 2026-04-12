package com.example.whatifinished.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.whatifinished.model.ActivityItem
import com.example.whatifinished.model.Category
import com.example.whatifinished.ui.components.ActivityCard
import com.example.whatifinished.ui.components.EmptyStateMessage
import com.example.whatifinished.ui.components.LoadingSpinner
import com.example.whatifinished.ui.theme.Dimens
import com.example.whatifinished.ui.theme.WhatIFinishedTheme
import com.example.whatifinished.viewmodel.MainUiState
import com.example.whatifinished.viewmodel.MainViewModel

@Composable
fun MainListScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    MainListScreen(
        uiState = uiState.value,
        onRetry = viewModel::loadActivities
    )
}

@Composable
fun MainListScreen(
    uiState: MainUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        when (uiState) {
            MainUiState.Loading -> LoadingSpinner(
                modifier = Modifier.padding(innerPadding)
            )

            MainUiState.Empty -> EmptyStateMessage(
                title = "Nothing finished yet",
                description = "Your completed games, books, films, and courses will appear here.",
                modifier = Modifier.padding(innerPadding)
            )

            is MainUiState.Error -> EmptyStateMessage(
                title = "Could not load activities",
                description = uiState.message,
                actionLabel = "Retry",
                onAction = onRetry,
                modifier = Modifier.padding(innerPadding)
            )

            is MainUiState.Success -> MainListContent(
                activities = uiState.activities,
                contentPadding = innerPadding
            )
        }
    }
}

@Composable
fun MainListContent(
    activities: List<ActivityItem>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = Dimens.ScreenPadding,
            top = Dimens.ScreenPadding + contentPadding.calculateTopPadding(),
            end = Dimens.ScreenPadding,
            bottom = Dimens.ScreenPadding + contentPadding.calculateBottomPadding()
        ),
        verticalArrangement = Arrangement.spacedBy(Dimens.CardSpacing)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimens.SectionSpacing),
                verticalArrangement = Arrangement.spacedBy(Dimens.ContentSpacing)
            ) {
                Text(
                    text = "What I Finished",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "A running list of completed activities across every category.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        items(
            items = activities,
            key = { activity -> activity.id }
        ) { activity ->
            ActivityCard(activity = activity)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainListContentPreview() {
    WhatIFinishedTheme {
        MainListScreen(
            uiState = MainUiState.Success(
                activities = previewActivities()
            ),
            onRetry = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainListEmptyPreview() {
    WhatIFinishedTheme {
        MainListScreen(
            uiState = MainUiState.Empty,
            onRetry = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainListLoadingPreview() {
    WhatIFinishedTheme {
        MainListScreen(
            uiState = MainUiState.Loading,
            onRetry = {}
        )
    }
}

private fun previewActivities() = listOf(
    ActivityItem(
        id = 1L,
        title = "Elden Ring",
        completionDate = "2026-04-10",
        rating = 5,
        notes = "Exploration was worth every hour.",
        category = Category.GAMES
    ),
    ActivityItem(
        id = 2L,
        title = "Clean Architecture",
        completionDate = "2026-04-02",
        rating = 4,
        notes = "Strong revisit on boundaries and use cases.",
        category = Category.BOOKS
    )
)
