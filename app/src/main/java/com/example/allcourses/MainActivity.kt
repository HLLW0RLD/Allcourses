package com.example.allcourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.allcourses.data.storage.UserStorage
import com.example.allcourses.ui.screen.BottomBarState
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
                        Modifier.fillMaxSize(),
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = startRoute!!.route()
                        ) {

                            animatedScreenComposable<Login>(
                                navController = navController,
                            ) { LoginScreen() }

                            animatedScreenComposable<MainFeed>(
                                navController = navController,
                            ) { MainFeedScreen() }
                        }

                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route

                        if (currentRoute in listOf(
                                MainFeed.route(),
                                Login.route(),
//                            MainFeed
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
        modifier = modifier.fillMaxWidth(),
        containerColor = AppColors.surface,
    ) {
        NavigationBarItem(
            selected = currentRoute == MainFeed.route(),
            onClick = {
                if (currentRoute != MainFeed.route()) {
                    navController.nav(MainFeed)
                }
            },
            icon = { painterResource(R.drawable.ic_favorites_checked) },
            label = { Text("Главная") }
        )

        NavigationBarItem(
            selected = currentRoute == Login.route(),
            onClick = {
                if (currentRoute != Login.route()) {
                    navController.nav(Login)
                }
            },
            icon = { painterResource(R.drawable.ic_favorites_checked) },
            label = { Text("Избранное") }
        )

//        NavigationBarItem(
//            selected = currentRoute == Screen.Profile.route,
//            onClick = {
//                if (currentRoute != Screen.Profile.route) {
//                    navController.nav(Screen.Profile.route)
//                }
//            },
//            icon = { painterResource(R.drawable.ic_favorites_checked) },
//            label = { Text("Профиль") }
//        )
    }
}