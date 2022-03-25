package com.dc.kavach.domain.repository

interface LocalAppRepository {

    suspend fun getLocalAppsList() : List<String>

}