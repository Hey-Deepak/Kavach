package com.dc.kavach.data.repository.firebase

import com.dc.kavach.domain.repository.UserBannedAppCountRepository
import com.dc.kavach.other.ResultOf

class UserBannedAppCountRepositoryImpl(
    userEmail: String
) : UserBannedAppCountRepository {

    override suspend fun updateCountOnServer(count: Int): ResultOf<Unit> {
        TODO("Not yet implemented")
    }
}