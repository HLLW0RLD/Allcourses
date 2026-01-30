package com.example.allcourses.data.di

import com.example.allcourses.ui.screen.viewModel.FavoritesViewModel
import com.example.allcourses.ui.screen.viewModel.LoginViewModel
import com.example.allcourses.ui.screen.viewModel.MainFeedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel() }
    viewModel { MainFeedViewModel(get(), get()) }
    viewModel { FavoritesViewModel(get()) }
}