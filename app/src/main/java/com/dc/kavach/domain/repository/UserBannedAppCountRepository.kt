package com.dc.kavach.domain.repository

import com.dc.kavach.other.ResultOf

interface UserBannedAppCountRepository {

    suspend fun updateCountOnServer(count: Int): ResultOf<Unit>

}