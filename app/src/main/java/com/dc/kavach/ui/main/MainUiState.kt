package com.dc.kavach.ui.main

import com.dc.kavach.domain.models.BannedApp

sealed class MainUiState {
    object NotLoggedIn: MainUiState()
    object Loading: MainUiState()
    data class Error(val msg: String): MainUiState()
    data class LoggedIn(val emailId: String): MainUiState()
    //data class Main(val bannedApps: BannedApps): MainUiState()
    data class FilteredAppList(val filteredAppList: List<BannedApp>): MainUiState()
}
