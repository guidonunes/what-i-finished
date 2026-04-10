package com.example.whatifinished.ui

import androidx.lifecycle.ViewModel
import com.example.whatifinished.data.GreetingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val greetingRepository: GreetingRepository
) : ViewModel() {
    val greetingMessage: String = greetingRepository.greeting()
}
