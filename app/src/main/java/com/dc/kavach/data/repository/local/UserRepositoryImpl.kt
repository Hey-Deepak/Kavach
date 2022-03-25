package com.dc.kavach.data.repository.local

import android.content.SharedPreferences
import com.dc.kavach.domain.repository.UserRepository

class UserRepositoryImpl(
    prefs: SharedPreferences
) : UserRepository {

    override fun getLoggedInEmailFromPrefs(): String {
        TODO("Not yet implemented")
    }

    override fun saveEmailToPrefs(email: String) {
        TODO("Not yet implemented")
    }
}