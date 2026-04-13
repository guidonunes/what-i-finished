package com.example.whatifinished.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.whatifinished.model.ActivityItem
import com.example.whatifinished.model.Category
import com.example.whatifinished.ui.extensions.toFormattedDate
import com.example.whatifinished.ui.theme.CategoryBooks
import com.example.whatifinished.ui.theme.CategoryCourses
import com.example.whatifinished.ui.theme.CategoryFilms
import com.example.whatifinished.ui.theme.CategoryGames
import com.example.whatifinished.ui.theme.CategoryOthers
import com.example.whatifinished.ui.theme.Dimens
import com.example.whatifinished.ui.theme.WhatIFinishedTheme

@Composable
fun ActivityCard(
    activity: ActivityItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(Dimens.CardSpacing)
    ) {
        Column(
            modifier = Modifier.padding(Dimens.CardPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.ContentSpacing)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = activity.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                activity.rating?.let { rating ->
                    RatingStars(rating = rating)
                }
            }

            CategoryChip(category = activity.category)

            Text(
                text = activity.completionDate.toFormattedDate(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (activity.notes.isNotBlank()) {
                Text(
                    text = activity.notes,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun CategoryChip(
    category: Category,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(999.dp),
        color = category.color()
    ) {
        Text(
            text = category.displayName(),
            modifier = Modifier.padding(
                horizontal = Dimens.ChipHorizontalPadding,
                vertical = Dimens.ChipVerticalPadding
            ),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

private fun Category.color() = when (this) {
    Category.GAMES -> CategoryGames
    Category.BOOKS -> CategoryBooks
    Category.FILMS -> CategoryFilms
    Category.COURSES -> CategoryCourses
    Category.OTHERS, Category.UNKNOWN -> CategoryOthers
}

private fun Category.displayName() = when (this) {
    Category.GAMES -> "Games"
    Category.BOOKS -> "Books"
    Category.FILMS -> "Films"
    Category.COURSES -> "Courses"
    Category.OTHERS -> "Others"
    Category.UNKNOWN -> "Unknown"
}

@Preview
@Composable
private fun ActivityCardPreview() {
    WhatIFinishedTheme {
        ActivityCard(
            activity = ActivityItem(
                id = 1L,
                title = "The Witcher 3",
                category = Category.GAMES,
                rating = 3,
                completionDate = "2026-04-12",
                notes = "Finished all main quests"
            )
        )
    }
}



