package com.dc.kavach.domain.repository

import com.dc.kavach.domain.models.BannedApps
import com.dc.kavach.other.ResultOf

interface BannedAppsRepository {

    suspend fun fetchBannedApps(): ResultOf<BannedApps>

    suspend fun setLastAppOpenTime(emailId: String)

}