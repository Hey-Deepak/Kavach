package com.dc.kavach.domain.repository

interface UserRepository {

    fun getLoggedInEmailFromPrefs(): String?

    fun saveEmailToPrefs(email: String)

}