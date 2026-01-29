package com.example.allcourses.ui.screen.viewModel

import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allcourses.data.model.UserData
import com.example.allcourses.data.storage.UserStorage
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    fun saveUser(email: String, password: String) {
        viewModelScope.launch {
            val user = UserData(email = email, password = password)
            UserStorage.saveUser(user)
        }
    }
}