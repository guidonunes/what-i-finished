package com.example.whatifinished.ui

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.whatifinished.repository.ActivityRepository
import com.example.whatifinished.repository.RepositoryResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val activityRepository: ActivityRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadActivities()
    }

    fun loadActivities() {
        viewModelScope.launch {
            _uiState.value = MainUiState.Loading
            _uiState.value = when (val result = activityRepository.getActivities()) {
                is RepositoryResult.Success -> {
                    if (result.data.isEmpty()) {
                        MainUiState.Empty
                    } else {
                        MainUiState.Success(result.data)
                    }
                }

                is RepositoryResult.Error -> MainUiState.Error(result.message)
            }
        }
    }
}
