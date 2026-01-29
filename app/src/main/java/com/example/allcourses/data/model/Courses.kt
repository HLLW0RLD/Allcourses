package com.example.allcourses.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Courses(
    @SerializedName("courses")
    val courses: List<Course>
)

@Serializable
data class Course(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("hasLike")
    val hasLike: Boolean,
    @SerializedName("publishDate")
    val publishDate: String
)