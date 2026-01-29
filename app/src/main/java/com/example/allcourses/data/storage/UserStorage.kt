package com.example.allcourses.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.allcourses.App
import com.example.allcourses.data.model.UserData
import com.google.gson.Gson
import kotlinx.coroutines.flow.first

object UserStorage {
    val context = App.instance
    private val gson = Gson()

    private const val USER_PREFERENCES = "user_prefs"
    private val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES)

    private val USER_KEY = stringPreferencesKey("user_json")

    suspend fun saveUser(user: UserData) {
        context.dataStore.edit { prefs ->
            prefs[USER_KEY] = gson.toJson(user)
        }
    }

    suspend fun getUser(): UserData? {
        val json = context.dataStore.data.first()[USER_KEY] ?: return null
        return gson.fromJson(json, UserData::class.java)
    }
}