package com.dc.kavach.ui.main

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import com.dc.kavach.ui.main.composables.*

@Composable
fun MainScreen(
    loginLauncher: ActivityResultLauncher<Intent>,
    viewModel: MainViewModel
) {

    when(viewModel.uiState.value) {
        MainUiState.NotLoggedIn -> {
            NotLoggedIn(loginLauncher)
        }
        is MainUiState.Error -> {
            val msg = (viewModel.uiState.value as MainUiState.Error).msg
            Error(msg)
        }
        is MainUiState.Loading -> {
            Loading()
        }
        is MainUiState.LoggedIn -> {
            val email = (viewModel.uiState.value as MainUiState.LoggedIn).emailId
            LoggedIn(email)

        }
//        is MainUiState.Main -> {
//            val data = (viewModel.uiState.value as MainUiState.Main).bannedApps
//            Main(data)
//        }

        is MainUiState.FilteredAppList -> {
            val mainList = (viewModel.uiState.value as MainUiState.FilteredAppList).filteredAppList
            FilteredAppList(filteredAppList = mainList)
        }
    }

}