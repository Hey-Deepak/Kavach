package com.dc.kavach.data.repository.local

import android.content.pm.PackageManager
import com.dc.kavach.domain.repository.LocalAppRepository

class LocalAppRepositoryImpl(
    packageManager: PackageManager
) : LocalAppRepository {

    override suspend fun getLocalAppsList(): List<String> {
        TODO("Not yet implemented")
    }
}