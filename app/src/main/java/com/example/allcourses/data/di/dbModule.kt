package com.example.allcourses.data.di

import androidx.room.Room
import com.example.allcourses.data.db.CourseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            CourseDatabase::class.java,
            "courses.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<CourseDatabase>().postDao() }
}