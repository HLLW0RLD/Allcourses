package com.example.allcourses.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.allcourses.R
import com.example.allcourses.ui.CourseItem
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
        favoritesViewModel.getFavorites()
    }

    val favorites by favoritesViewModel.favorites.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            text = stringResource(R.string.favorites),
            fontSize = 30.sp,
            color = AppColors.textPrimary,
            textAlign = TextAlign.Start
        )
        Spacer(Modifier.size(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ) {
            items(favorites.size) { i ->
                val course = favorites[i]

                CourseItem(
                    title = course.title,
                    description = course.text,
                    price = course.price,
                    rating = course.rate,
                    date = course.publishDate,
                    hasLike = true,
                    onClick = {

                    },
                    onFavorites = {

                    }
                )
                Spacer(Modifier.size(12.dp))
            }
        }
    }
}