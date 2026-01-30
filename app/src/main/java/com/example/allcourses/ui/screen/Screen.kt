package com.example.allcourses.ui.screen

import android.util.Base64
import androidx.activity.compose.BackHandler
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
sealed interface Screen

fun Screen.route(): String {
    val type = this::class.simpleName ?: error("No class name for $this")

    val isObject = this::class.objectInstance != null
    return if (isObject) {
        type
    } else {
        val encoded = encodeToBase64(this)
        "$type/$encoded"
    }
}

fun NavController.nav(
    screen : Screen
) {
    val route = screen.route()
    val page = route.substringBefore("/")
    val current = currentBackStackEntry?.destination?.route?.substringBefore("/") ?: ""

    if (page == current) {
        return
    }

    navigate(route) {
        launchSingleTop = true
    }
}

inline fun <reified T : Screen> NavGraphBuilder.animatedScreenComposable(
    navController: NavController,
    crossinline content: @Composable (T) -> Unit
) {
    val typeName = T::class.simpleName ?: error("No class name for ${T::class}")
    val objectInstance = T::class.objectInstance

    if (objectInstance != null) {
        composable(
            route = typeName,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) {
            BackHandler { navController.popBackStack() }
            content(objectInstance)
        }
        return
    }

    composable(
        route = "$typeName/{encoded}",
        arguments = listOf(navArgument("encoded") { type = NavType.StringType }),
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() }
    ) { backStackEntry ->
        val encoded = backStackEntry.arguments?.getString("encoded")
            ?: error("Missing encoded argument for $typeName")

        val screen = decodeFromBase64<T>(encoded)

        BackHandler { navController.popBackStack() }
        content(screen)
    }
}

fun enterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(300)
    )
}

fun exitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(300)
    )
}

fun popEnterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(300)
    )
}

fun popExitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(300)
    )
}

val jsonFormat = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
}

inline fun <reified T> encodeToBase64(obj: T): String {
    val json = jsonFormat.encodeToString(obj)
    return Base64.encodeToString(json.toByteArray(), Base64.URL_SAFE or Base64.NO_WRAP)
}

inline fun <reified T> decodeFromBase64(encoded: String): T {
    val json = String(Base64.decode(encoded, Base64.URL_SAFE or Base64.NO_WRAP))
    return jsonFormat.decodeFromString(json)
}


class BottomBarState  {
    var enabled by mutableStateOf(true)
    var visible by mutableStateOf(true)
}
val LocalBottomBarState =
    staticCompositionLocalOf<BottomBarState > {
        error("BottomBarState not provided")
    }