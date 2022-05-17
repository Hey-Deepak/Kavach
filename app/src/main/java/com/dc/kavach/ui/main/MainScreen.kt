package com.dc.kavach.ui.main

import android.content.Intent
import android.util.Log
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
            Log.d("TAG ", "1")
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
            Log.d("TAG ", "2")
            val email = (viewModel.uiState.value as MainUiState.LoggedIn).emailId
            LoggedIn(email)
        }
        is MainUiState.FilteredAppList -> {
            val mainList = (viewModel.uiState.value as MainUiState.FilteredAppList).filteredAppList
            FilteredAppList(filteredAppList = mainList)
        }
    }

}