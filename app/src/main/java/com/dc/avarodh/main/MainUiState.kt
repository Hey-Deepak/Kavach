package com.dc.avarodh.main

import com.dc.avarodh.model.BannedApps

sealed class MainUiState {
    object NotLoggedIn: MainUiState()
    object Loading: MainUiState()
    data class Error(val msg: String): MainUiState()
    data class LoggedIn(val emailId: String): MainUiState()
    data class Main(val bannedApps: BannedApps): MainUiState()
    data class FilteredAppList(val filteredAppList: MutableList<String>): MainUiState()
}
