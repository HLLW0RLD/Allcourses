package com.example.allcourses.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.allcourses.data.model.CourseEntity

@Database(
    entities = [CourseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CourseDatabase: RoomDatabase() {
    abstract fun postDao(): CourseDao
}