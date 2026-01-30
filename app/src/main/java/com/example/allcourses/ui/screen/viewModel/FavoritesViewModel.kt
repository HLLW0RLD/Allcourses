package com.example.allcourses.ui.screen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allcourses.data.model.Course
import com.example.allcourses.data.repo.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: FavoritesRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<Course>>(emptyList())
    val favorites = _favorites.asStateFlow()

    fun getFavorites() {
        viewModelScope.launch {
            _favorites.value  = repository.getFavorites()
        }
    }
}