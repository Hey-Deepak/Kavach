package com.dc.avarodh.main

import android.content.pm.PackageManager
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dc.avarodh.model.BannedApp
import com.dc.avarodh.model.BannedApps
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val packageManager: PackageManager
): ViewModel(){

    val uiState = mutableStateOf<MainUiState>(MainUiState.NotLoggedIn)

    init {
        /*if(uiState.value is MainUiState.LoggedIn)
            fetchList()*/
    }

    fun pushList() {
        val db = FirebaseFirestore.getInstance()
        db.collection("data").document("BannedAppList")
            .set(
                BannedApps(
                listOf(
                    BannedApp("a.b.c"),
                    BannedApp("a.b.c1"),
                    BannedApp("a.b.c2"),
                    BannedApp("a.b.c3"),
                    BannedApp("a.b.c4"),
                )
            )
            )
    }

    fun fetchList() {
        val db = FirebaseFirestore.getInstance()
        db.collection("data").document("BannedAppList").get()
            .addOnSuccessListener {
                if(it.exists()){
                    val list = it.toObject(BannedApps::class.java)
                    list?.let {
                        uiState.value = MainUiState.Main(it)
                    } ?: kotlin.run {
                        uiState.value = MainUiState.Error("Unable to deserialize data!")
                    }
                } else {
                    uiState.value = MainUiState.Error("Data not found!!")
                }
            }.addOnFailureListener{
                uiState.value = MainUiState.Error("Unable to get data! Error : ${it.message}")
            }
    }

}