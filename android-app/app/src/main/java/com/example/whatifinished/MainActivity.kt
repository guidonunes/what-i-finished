package com.example.whatifinished

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.whatifinished.ui.screens.MainListScreen
import com.example.whatifinished.ui.theme.WhatIFinishedTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatIFinishedTheme {
                Surface {
                    MainListScreen()
                }
            }
        }
    }
}
