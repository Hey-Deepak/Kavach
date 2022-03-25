package com.dc.kavach.domain.usecase

import com.dc.kavach.domain.repository.BannedAppsRepository
import com.dc.kavach.domain.repository.LocalAppRepository
import com.dc.kavach.domain.models.BannedApp
import com.dc.kavach.other.ResultOf

class BannedAppsUsecase(
    val appRepository: BannedAppsRepository,
    val localAppRepository: LocalAppRepository
) {

    suspend fun getBannedApps(): ResultOf<List<BannedApp>> {
        TODO()
    }

    suspend fun refreshList(): List<BannedApp> {
        TODO()
    }

}