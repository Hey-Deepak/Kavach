package com.dc.kavach.data.repository.local

import android.content.pm.PackageManager
import com.dc.kavach.domain.repository.LocalAppRepository

class LocalAppRepositoryImpl(
    val packageManager: PackageManager
) : LocalAppRepository {

    override fun getLocalAppsList(): List<String> {
        val localAppslist : MutableList<String> = mutableListOf()
        packageManager.getInstalledApplications(PackageManager.GET_META_DATA).forEach {
            localAppslist.add(it.packageName)
        }
        return localAppslist
    }
}