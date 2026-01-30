package com.example.allcourses.data.repo

import com.example.allcourses.data.db.CourseDao
import com.example.allcourses.data.model.Course
import com.example.allcourses.data.model.toEntity
import com.example.allcourses.data.model.toModel
import kotlinx.coroutines.flow.firstOrNull

class FavoritesRepository(
    private val courseDao: CourseDao
) {

    suspend fun getFavorites(): List<Course> {
        return courseDao.getFavorites()
            .firstOrNull()
            ?.map { it.toModel() }
            ?: emptyList()
    }

    suspend fun saveCourses(course: Course) {
        courseDao.insert(course.toEntity())
    }
}