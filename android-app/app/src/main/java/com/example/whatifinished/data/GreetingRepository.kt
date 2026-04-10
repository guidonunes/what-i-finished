package com.example.whatifinished.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GreetingRepository @Inject constructor() {
    fun greeting(): String = "Jetpack Compose + Hilt are ready."
}
