package com.example.whatifinished.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.whatifinished.ui.theme.StarYellow

@Composable
fun RatingStars(
    rating: Int,
    maxRating: Int = 5
) {
    val safeRating = rating.coerceIn(0, maxRating)

    Row {
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= safeRating) {
                    Icons.Filled.Star
                } else {
                    Icons.Filled.StarBorder
                },
                contentDescription = "Rating Stars",
                tint = if (i <= safeRating) {
                    StarYellow
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
    }
}
