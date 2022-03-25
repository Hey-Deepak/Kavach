package com.dc.kavach.domain.usecase

import com.dc.kavach.domain.repository.BannedAppsRepository
import com.dc.kavach.domain.repository.LocalAppRepository
import com.dc.kavach.domain.models.BannedApp
import com.dc.kavach.domain.models.BannedApps
import com.dc.kavach.other.ResultOf
import java.lang.Exception

class BannedAppsUsecase(
    val appRepository: BannedAppsRepository,
    val localAppRepository: LocalAppRepository
) {
    val filteredAppList = mutableListOf<BannedApp>()

    suspend fun getBannedApps(): ResultOf<List<BannedApp>> {
        return when(
            val result = appRepository.fetchBannedApps()
        ) {
            is ResultOf.Success -> {
                val bannedApps = result.data!!.apps
                val localAppList = localAppRepository.getLocalAppsList()
                ResultOf.Success(filterList(bannedApps, localAppList))
            }
            is ResultOf.Error -> {
                ResultOf.Error(result.message!!)
            }
        }
    }

    suspend fun getBannedAppsAlt(): ResultOf<List<BannedApp>> {
        return try {
            val bannedApps = appRepository.fetchBannedApps().await()
            val localAppList = localAppRepository.getLocalAppsList()
            ResultOf.Success(filterList(bannedApps.apps, localAppList))
        } catch (e: Exception) {
            ResultOf.Error(e.message.toString())
        }
    }

    private fun filterList(bannedApps: List<BannedApp>, localAppList: List<String>): List<BannedApp> {

        for (localApp in localAppList){
            for (bannedApp in bannedApps){
                if (localApp.equals (bannedApp.packageName)){
                    filteredAppList.add(bannedApp)
                    break
                }
            }
        }
        return filteredAppList.toList()
    }

    suspend fun refreshList(): List<BannedApp> {
        val localAppList = localAppRepository.getLocalAppsList()
        return filterList(filteredAppList, localAppList)
    }

}