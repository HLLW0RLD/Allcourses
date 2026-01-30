package com.example.allcourses.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.capitalize
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern


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

fun parseDate(dateString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("ru"))

        val date = inputFormat.parse(dateString)
        val formatted = outputFormat.format(date!!)

        val pattern = Regex("""(\d+)\s+([а-яё]+)\s+(\d+)""")

        return pattern.replace(formatted) { matchResult ->
            val day = matchResult.groupValues[1]
            val month = matchResult.groupValues[2]
            val year = matchResult.groupValues[3]

            val capitalizedMonth = month.replaceFirstChar {
                it.titlecase(Locale("ru"))
            }

            "$day $capitalizedMonth $year"
        }
    } catch (e: Exception) {
        dateString
    }
}