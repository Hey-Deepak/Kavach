package com.dc.avarodh.main

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import com.dc.avarodh.main.composables.*

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
        is MainUiState.Main -> {
            val data = (viewModel.uiState.value as MainUiState.Main).bannedApps
            Main(data)
        }

        is MainUiState.FilteredAppList -> {
            val mainList = (viewModel.uiState.value as MainUiState.FilteredAppList).filteredAppList
            Log.d("TAG5", "FilteredList5" + mainList.toString())
            FilteredAppList(filteredAppList = mainList)
        }
    }

}