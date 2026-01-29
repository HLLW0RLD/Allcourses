package com.example.allcourses.ui.screen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allcourses.data.model.Course
import com.example.allcourses.data.repo.CoursesRepository
import com.example.allcourses.utils.logError
import com.example.allcourses.utils.logSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainFeedViewModel(
    private val repository: CoursesRepository
): ViewModel() {

    private val _courses = MutableStateFlow<CoursesStatus>(CoursesStatus.Loading)
    val courses = _courses.asStateFlow()

    fun getCourses() {
        viewModelScope.launch {
            _courses.value = CoursesStatus.Loading

            repository.getCourses().fold(
                onSuccess = { coursesList ->
                    _courses.value = CoursesStatus.Success(coursesList)
                    logSuccess("downloaded:\n${_courses.value}")
                },
                onFailure = { err ->
                    _courses.value = CoursesStatus.Error
                    logError(err)
                }
            )
        }
    }
}

sealed class CoursesStatus {
    object Error: CoursesStatus()
    object Loading: CoursesStatus()
    data class Success(val courses : List<Course>?): CoursesStatus()
}