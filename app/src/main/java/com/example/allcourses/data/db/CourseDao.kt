package com.example.allcourses.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.allcourses.data.model.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: CourseEntity)

    @Query("SELECT * FROM course")
    fun getFavorites(): Flow<List<CourseEntity>>
}