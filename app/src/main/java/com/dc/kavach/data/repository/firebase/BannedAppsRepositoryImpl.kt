package com.dc.kavach.data.repository.firebase

import com.dc.kavach.domain.repository.BannedAppsRepository
import com.dc.kavach.domain.models.BannedApps
import com.dc.kavach.other.ResultOf
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class BannedAppsRepositoryImpl : BannedAppsRepository {

    val db = FirebaseFirestore.getInstance()

    override suspend fun fetchBannedApps(): ResultOf<BannedApps> {
        return suspendCoroutine { continuation ->
            db.collection("data").document("BannedAppList").get()
                .addOnSuccessListener {
                    if(it.exists()){
                        val list = it.toObject(BannedApps::class.java)
                        list?.let {
                            continuation.resume(
                                ResultOf.Success(list)
                            )
                        } ?: kotlin.run {
                            continuation.resume(
                                ResultOf.Error("Unable to deserialize data!")
                            )
                        }
                    } else {
                        continuation.resume(
                            ResultOf.Error("Data not found!!")
                        )
                    }
                }.addOnFailureListener {
                    continuation.resume(
                        ResultOf.Error("Unable to get data! Error : ${it.message}")
                    )
                }
        }
    }

}