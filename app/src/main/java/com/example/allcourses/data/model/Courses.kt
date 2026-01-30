package com.example.allcourses.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
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


@Entity(tableName = "course")
data class CourseEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)

fun Course.toEntity() = CourseEntity(
    id = this.id,
    title = this.title,
    text = this.text,
    price = this.price,
    rate = this.rate,
    startDate = this.startDate,
    hasLike = this.hasLike,
    publishDate = this.publishDate,
)

fun CourseEntity.toModel() = Course(
    id = this.id,
    title = this.title,
    text = this.text,
    price = this.price,
    rate = this.rate,
    startDate = this.startDate,
    hasLike = this.hasLike,
    publishDate = this.publishDate,
)