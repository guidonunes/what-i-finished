package com.example.whatifinished.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Map our custom colors to the Material 3 design system slots
private val DarkColorPalette = darkColorScheme(
    background = DeepBackground,
    surface = SurfaceDark,
    surfaceVariant = SurfaceDark, // Used for things like text fields or bottom sheets
    primary = PrimaryAccent,
    onPrimary = OnPrimaryText,
    onBackground = PrimaryText,
    onSurface = PrimaryText,
    onSurfaceVariant = SecondaryText
)

@Composable
fun WhatIFinishedTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current

    // This block colors the very top of your phone screen (where the battery/wifi icons are)
    // to match your app's dark background seamlessly.
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = DarkColorPalette.background.toArgb()
            window.navigationBarColor = DarkColorPalette.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = DarkColorPalette,
        typography = Typography, // This uses the default Type.kt file
        content = content
    )
}