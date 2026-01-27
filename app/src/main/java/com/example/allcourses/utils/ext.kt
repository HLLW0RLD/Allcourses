package com.example.allcourses.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import java.util.regex.Matcher
import java.util.regex.Pattern


internal fun Context.findActivity(): ComponentActivity {
    var context = this
    while (context is ContextWrapper) {
        if (context is ComponentActivity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Picture in picture should be called in the context of an Activity")
}

val LocalNavController =
    staticCompositionLocalOf<NavController> { throw IllegalStateException("No NavController found") }


fun toast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}

fun openLink(context: Context, link: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context.startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
        toast(context, "Не удалось открыть ссылку")
    }
}

fun isValidEmail(email: String): Boolean {
    var isValid = false
    val expression = "^[a-zA-Z0-9+_.-]+@[a-z]+\\.+[a-z]+"
    val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(email)
    if (matcher.matches()) {
        isValid = true
    }
    return isValid
}