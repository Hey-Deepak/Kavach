package com.dc.kavach.data.repository.firebase

import com.dc.kavach.domain.repository.UserBannedAppCountRepository
import com.dc.kavach.other.ResultOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserBannedAppCountRepositoryImpl() : UserBannedAppCountRepository {
    val db = FirebaseFirestore.getInstance()

    override suspend fun updateCountOnServer(count: Int): ResultOf<Unit> {
        val user = FirebaseAuth.getInstance().currentUser ?: return ResultOf.Error("User not logged In")
        val mailId = user.email ?: return ResultOf.Error("Main Id not found")
        val userName = user.displayName ?: "NA"

        return try {
            db.collection("Users").document(mailId).set(
                mapOf(
                    "count" to count,
                    "userName" to userName
                )
            ).await()
            ResultOf.Success(Unit)
        } catch (e : Exception){
            ResultOf.Error(e.message.toString())
        }
    }
}