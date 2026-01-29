package com.example.allcourses.data.di

import com.example.allcourses.data.repo.CoursesRepository
import org.koin.dsl.module

val repoModule = module  {
    single {
        CoursesRepository(get())
    }
}