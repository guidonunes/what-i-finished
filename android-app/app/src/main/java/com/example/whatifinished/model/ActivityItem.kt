package com.example.whatifinished.model

import com.example.whatifinished.data.ActivityDto

// This is the clean model your Jetpack Compose screens will actually use
data class ActivityItem(
    val id: Long = 0L, // UI doesn't have to worry about nulls
    val title: String,
    val completionDate: String,
    val rating: Int?,
    val notes: String = "", // Default to empty string instead of null
    val category: Category
)

// The Mapper: Translates the raw DTO into the clean UI Model
fun ActivityDto.toDomainModel(): ActivityItem {
    return ActivityItem(
        id = this.id ?: 0L,
        title = this.title,
        completionDate = this.completionDate,
        rating = this.rating,
        notes = this.notes ?: "",
        // Safely convert the string to an Enum, fallback to UNKNOWN if it fails
        category = try {
            Category.valueOf(this.category.uppercase())
        } catch (e: IllegalArgumentException) {
            Category.UNKNOWN
        }
    )
}

// The Mapper: Translates UI model back to DTO for POST/PUT requests
fun ActivityItem.toDto(): ActivityDto {
    return ActivityDto(
        id = if (this.id == 0L) null else this.id,
        title = this.title,
        completionDate = this.completionDate,
        rating = this.rating,
        notes = this.notes.ifBlank { null },
        category = this.category.name
    )
}
