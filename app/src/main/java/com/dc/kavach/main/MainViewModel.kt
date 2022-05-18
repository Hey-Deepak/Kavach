package com.dc.kavach.main

import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dc.kavach.model.BannedApp
import com.dc.kavach.model.BannedApps
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val packageManager: PackageManager
): ViewModel(){

    val uiState = mutableStateOf<MainUiState>(MainUiState.NotLoggedIn)
    private var filteredAppList: MutableList<BannedApp> = mutableListOf()
    private var listOfBannedApps: BannedApps = BannedApps(emptyList())


    fun fetchList() {
        uiState.value = MainUiState.Loading

        val db = FirebaseFirestore.getInstance()
        db.collection("data").document("BannedAppList").get()
            .addOnSuccessListener {
                if(it.exists()){
                    val list = it.toObject(BannedApps::class.java)
                    list?.let {

                        val localAppList: List<String> = getLocalappList()
                        listOfBannedApps = list
                        filterList(localAppList)

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

    fun filterList(localAppList: List<String>) {
        filteredAppList = mutableListOf()
        for (localApp in localAppList){
            for (bannedApp in listOfBannedApps.apps){
                if (localApp.equals (bannedApp.packageName)){
                    filteredAppList.add(bannedApp)
                } else -1
            }
        }
        Log.d("TAG3", "FilteredList ${filteredAppList.toString()}")
        uiState.value = MainUiState.FilteredAppList(filteredAppList)

        updateCountOnServer()
    }

    private fun updateCountOnServer() {
        val db = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance().currentUser?: return
        val mailId = user.email ?: return
        val userName = user.displayName ?: "NA"

        db.collection("Users").document(mailId).set(
            mapOf(
                "count" to filteredAppList.size,
                "userName" to userName
            )
        )
    }

    fun getLocalappList() : List<String> {
        val pm = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        val localAppList : MutableList<String> = mutableListOf()
        pm.forEach { localAppList.add(it.packageName) }
        return localAppList
    }
}
