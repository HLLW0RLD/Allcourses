package com.example.allcourses.data.repo

import com.example.allcourses.data.api.CoursesApi
import com.example.allcourses.data.model.Course
import com.example.allcourses.data.model.Courses
import com.example.allcourses.utils.logError
import com.example.allcourses.utils.logSuccess
import com.google.gson.Gson

class CoursesRepository(private val api: CoursesApi) {
    val gson = Gson()

    suspend fun getCourses(): Result<List<Course>> {
        return try {
            val response = api.getCourses()
            val json = response.string()
            val coursesResponse = gson.fromJson(json, Courses::class.java)

            logSuccess(coursesResponse.courses)
            Result.success(coursesResponse.courses)
        } catch (e: Exception) {
            logError(e)
            Result.failure(e)
        }
    }
}