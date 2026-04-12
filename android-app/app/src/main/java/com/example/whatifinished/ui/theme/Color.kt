package com.example.whatifinished.ui.theme

import androidx.compose.ui.graphics.Color

// --- Core Dark Theme Colors ---
val DeepBackground = Color(0xFF121212) // OLED-friendly deep charcoal/black
val SurfaceDark = Color(0xFF1E1E1E)    // Slightly lighter for cards to create elevation
val PrimaryAccent = Color(0xFFB388FF)  // A modern, vibrant neon purple
val OnPrimaryText = Color(0xFF121212)  // Dark text when sitting on top of the primary color
val PrimaryText = Color(0xFFE0E0E0)    // High contrast off-white for main text
val SecondaryText = Color(0xFFAAAAAA)  // Muted gray for dates and notes

// --- Category Chip Colors ---
// These will be used specifically for the pill-shaped tags on your ActivityCard
val CategoryGames = Color(0xFF00E676)   // Neon Mint Green
val CategoryBooks = Color(0xFFFFCA28)   // Amber
val CategoryFilms = Color(0xFFFF5252)   // Vibrant Red
val CategoryCourses = Color(0xFF448AFF) // Bright Blue
val CategoryOthers = Color(0xFF9E9E9E)  // Neutral Grey