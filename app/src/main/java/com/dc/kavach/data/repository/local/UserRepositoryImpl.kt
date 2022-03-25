package com.dc.kavach.data.repository.local

import android.content.SharedPreferences
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dc.kavach.domain.repository.UserRepository

class UserRepositoryImpl(
    val prefs: SharedPreferences
) : UserRepository {

    override fun getLoggedInEmailFromPrefs(): String?{
        return prefs.getString("emailId", null)
    }

    override fun saveEmailToPrefs(email: String) {
        prefs.edit().putString("emailId", email).apply()
    }
}