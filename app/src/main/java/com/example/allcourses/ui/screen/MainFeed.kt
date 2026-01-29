package com.example.allcourses.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.allcourses.ui.CourseItem
import com.example.allcourses.ui.screen.viewModel.CoursesStatus
import com.example.allcourses.ui.screen.viewModel.MainFeedViewModel
import com.example.allcourses.ui.theme.AppColors
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object MainFeed : Screen

@Composable
fun MainFeedScreen(
    mainFeedViewModel: MainFeedViewModel = koinViewModel()
) {

    LaunchedEffect(Unit) {
        mainFeedViewModel.getCourses()
    }

    val courses by mainFeedViewModel.courses.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(val items = courses) {
            is CoursesStatus.Success ->  {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 16.dp)
                ) {
                    items(items.courses?.size ?: 0) { i ->
                        val course = items.courses?.get(i)

                        CourseItem(
                            title = course?.title ?: "",
                            description = course?.text ?: "",
                            price = course?.price ?: "",
                            rating = course?.rate ?: "",
                            date = course?.publishDate ?: "",
                            hasLike = course?.hasLike ?: false,
                            onClick = {

                            },
                            onFavorites = {

                            }
                        )
                        Spacer(Modifier.size(12.dp))
                    }
                }
            }
            is CoursesStatus.Loading ->  {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is CoursesStatus.Error ->  {

            }
        }
    }
}