package com.example.allcourses.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.allcourses.R
import com.example.allcourses.ui.CourseItem
import com.example.allcourses.ui.CustomSearchField
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
    var isSorted by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomSearchField(
            value = "",
            onValueChange = {}
        )

        Row(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "По дате добавления",
                color = AppColors.primary,
                modifier = Modifier
                    .clickable {
                        isSorted = !isSorted
                        mainFeedViewModel.sortCoursesByDate(isSorted)
                    }
            )
            Spacer(Modifier.size(4.dp))
            Icon(
                painter = painterResource(R.drawable.flip_arrows),
                contentDescription = null,
                tint = AppColors.primary
            )
        }

        when(val items = courses) {
            is CoursesStatus.Success ->  {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp)
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
                                if (course != null) {
                                    mainFeedViewModel.addToFavorites(course)
                                }
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