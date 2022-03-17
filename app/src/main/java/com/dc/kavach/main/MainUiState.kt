package com.dc.kavach.main

import com.dc.kavach.model.BannedApp
import com.dc.kavach.model.BannedApps

sealed class MainUiState {
    object NotLoggedIn: MainUiState()
    object Loading: MainUiState()
    data class Error(val msg: String): MainUiState()
    data class LoggedIn(val emailId: String): MainUiState()
    data class Main(val bannedApps: BannedApps): MainUiState()
    data class FilteredAppList(val filteredAppList: MutableList<BannedApp>): MainUiState()
}
