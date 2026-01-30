package com.example.allcourses.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.allcourses.ui.screen.viewModel.FavoritesViewModel
import com.example.allcourses.ui.screen.viewModel.MainFeedViewModel
import com.example.allcourses.ui.theme.AppColors
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object FavoritesCourses : Screen

@Composable
fun FavoritesCoursesScreen(
    favoritesViewModel: FavoritesViewModel = koinViewModel()
) {

    LaunchedEffect(Unit) {
//        favoritesViewModel.getFavoritesCourses()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}