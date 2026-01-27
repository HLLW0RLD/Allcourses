package com.example.allcourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.allcourses.ui.screen.BottomBarState
import com.example.allcourses.ui.screen.LocalBottomBarState
import com.example.allcourses.ui.screen.Login
import com.example.allcourses.ui.screen.LoginScreen
import com.example.allcourses.ui.screen.MainFeed
import com.example.allcourses.ui.screen.MainFeedScreen
import com.example.allcourses.ui.screen.Screen
import com.example.allcourses.ui.screen.animatedScreenComposable
import com.example.allcourses.ui.screen.route
import com.example.allcourses.ui.theme.AllcoursesTheme
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
//                    val user = PersonalDataManager.getUser()
//                    startRoute = if (user != null) MainFeed else Login
                    startRoute = Login
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
                            // хост экран для табов
//                            animatedScreenComposable<TabsHost>(navController) {
//                                TabsHostScreen()
//                            }

                            animatedScreenComposable<Login>(
                                navController = navController,
                            ) { LoginScreen() }

                            animatedScreenComposable<MainFeed>(
                                navController = navController,
                            ) { MainFeedScreen() }
                        }
                    }
                }
            }
        }
    }
}