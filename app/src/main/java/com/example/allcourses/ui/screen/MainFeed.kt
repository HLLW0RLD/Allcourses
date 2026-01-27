package com.example.allcourses.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.allcourses.ui.theme.AppColors
import kotlinx.serialization.Serializable

@Serializable
object MainFeed : Screen

@Composable
fun MainFeedScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

    }
}