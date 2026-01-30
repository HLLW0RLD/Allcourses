package com.example.allcourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.allcourses.data.storage.UserStorage
import com.example.allcourses.ui.screen.BottomBarState
import com.example.allcourses.ui.screen.FavoritesCourses
import com.example.allcourses.ui.screen.FavoritesCoursesScreen
import com.example.allcourses.ui.screen.LocalBottomBarState
import com.example.allcourses.ui.screen.Login
import com.example.allcourses.ui.screen.LoginScreen
import com.example.allcourses.ui.screen.MainFeed
import com.example.allcourses.ui.screen.MainFeedScreen
import com.example.allcourses.ui.screen.Screen
import com.example.allcourses.ui.screen.animatedScreenComposable
import com.example.allcourses.ui.screen.nav
import com.example.allcourses.ui.screen.route
import com.example.allcourses.ui.theme.AllcoursesTheme
import com.example.allcourses.ui.theme.AppColors
import com.example.allcourses.utils.LocalNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AllcoursesTheme {

                val context = LocalContext.current

                val navController = rememberNavController()
                val bottomBarState = remember { BottomBarState() }

                var startRoute by remember { mutableStateOf<Screen?>(null) }
                var isInitialized by remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    val user = UserStorage.getUser()
                    startRoute = if (user != null) MainFeed else Login
                    isInitialized = true
                }

                if (!isInitialized) return@AllcoursesTheme

                CompositionLocalProvider(
                    LocalNavController provides navController,
                    LocalBottomBarState provides bottomBarState
                ){
                    Box(
                        Modifier
                            .statusBarsPadding()
                            .fillMaxSize(),
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = startRoute!!.route(),
                            modifier = Modifier.padding(bottom = 72.dp)
                        ) {

                            animatedScreenComposable<Login>(
                                navController = navController,
                            ) { LoginScreen() }

                            animatedScreenComposable<MainFeed>(
                                navController = navController,
                            ) { MainFeedScreen() }

                            animatedScreenComposable<FavoritesCourses>(
                                navController = navController,
                            ) { FavoritesCoursesScreen() }
                        }

                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route

                        if (currentRoute in listOf(
                                MainFeed.route(),
                                Login.route(),
                                FavoritesCourses.route(),
                            )
                        ) {
                            BottomNavBar(
                                modifier = Modifier.align(Alignment.BottomCenter),
                                currentRoute = currentRoute ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(
    modifier: Modifier,
    currentRoute: String
) {
    val navController = LocalNavController.current

    NavigationBar(
        modifier = modifier
            .fillMaxWidth(),
        containerColor = AppColors.surface,
    ) {
        NavigationBarItem(
            selected = currentRoute == MainFeed.route(),
            onClick = {
                navController.nav(MainFeed)
            },
            icon = {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(R.drawable.ic_home),
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.main),
                )
            },
            colors = NavigationBarItemColors(
                selectedIconColor = AppColors.primary,
                selectedTextColor = AppColors.primary,
                unselectedIconColor = AppColors.white,
                unselectedTextColor = AppColors.white,
                selectedIndicatorColor = AppColors.surface,
                disabledIconColor = AppColors.white,
                disabledTextColor = AppColors.white,
            )
        )

        NavigationBarItem(
            selected = currentRoute == FavoritesCourses.route(),
            onClick = {
                navController.nav(FavoritesCourses)
            },
            icon = {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(R.drawable.ic_favorites),
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.favorites),
                )
            },
            colors = NavigationBarItemColors(
                selectedIconColor = AppColors.primary,
                selectedTextColor = AppColors.primary,
                unselectedIconColor = AppColors.white,
                unselectedTextColor = AppColors.white,
                selectedIndicatorColor = AppColors.surface,
                disabledIconColor = AppColors.white,
                disabledTextColor = AppColors.white,
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(R.drawable.ic_profile),
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.account),
                )
            },
            colors = NavigationBarItemColors(
                selectedIconColor = AppColors.primary,
                selectedTextColor = AppColors.primary,
                unselectedIconColor = AppColors.white,
                unselectedTextColor = AppColors.white,
                selectedIndicatorColor = AppColors.surface,
                disabledIconColor = AppColors.white,
                disabledTextColor = AppColors.white,
            )
        )
    }
}