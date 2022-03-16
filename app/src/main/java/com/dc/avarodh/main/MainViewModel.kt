package com.dc.avarodh.main

import android.content.pm.PackageManager
import android.util.Log
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
    private val filteredAppList: MutableList<String> = mutableListOf()
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
                    BannedApp("com.lavish.toprestro}"),
                    BannedApp("com.dc.avarodh}"),
                    BannedApp("com.facebook.services}"),
                    BannedApp("com.asana.app}"),
                    BannedApp("com.shubham.sourcecodes}"),
                    BannedApp("com.honor.global}"),
                    BannedApp("com.whatsapp.w4b}"),
                    BannedApp("com.swapnil.noteapp}"),
                    BannedApp("com.app.nobrokerhood}")
                )
            )
            )
    }

    fun fetchList() {

        uiState.value = MainUiState.Loading

        val db = FirebaseFirestore.getInstance()
        db.collection("data").document("BannedAppList").get()
            .addOnSuccessListener {
                if(it.exists()){
                    val list = it.toObject(BannedApps::class.java)
                    list?.let {

                        val localAppList: List<String> = getLocalappList(packageManager)
                        filterList(localAppList, list)

                        
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

    private fun filterList(localAppList: List<String>, listOfBannedApps: BannedApps) {
        for (localApp in localAppList){
            for (bannedApp in listOfBannedApps.apps){
                if (localApp.substring(24).equals (bannedApp.packageName)){
                    filteredAppList.add(localApp.substring(24))
                } else -1
            }
        }
        Log.d("TAG3", "FilteredList ${filteredAppList.toString()}")
        uiState.value = MainUiState.FilteredAppList(filteredAppList)

    }

    private fun getLocalappList(pm: PackageManager) : List<String> {
        val pm = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        val localAppList : MutableList<String> = mutableListOf()
        pm.forEach { localAppList.add(it.toString()) }

        return localAppList
    }

}