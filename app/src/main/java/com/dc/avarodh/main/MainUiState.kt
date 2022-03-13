package com.dc.avarodh.main

sealed class MainUiState {
    object NotLoggedIn: MainUiState()
    object Loading: MainUiState()
    data class Error(val msg: String): MainUiState()
    data class LoggedIn(val emailId: String): MainUiState()
    data class Main(val appsList: List<BannedApp>): MainUiState()
}
