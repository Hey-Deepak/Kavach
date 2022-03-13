package com.dc.avarodh.main

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import com.dc.avarodh.main.composables.LoggedIn
import com.dc.avarodh.main.composables.NotLoggedIn

@Composable
fun MainScreen(
    loginLauncher: ActivityResultLauncher<Intent>,
    viewModel: MainViewModel
) {

    when(viewModel.uiState.value) {
        MainUiState.NotLoggedIn -> {
            NotLoggedIn(loginLauncher)
        }
        is MainUiState.Error -> TODO()
        is MainUiState.Loading -> TODO()
        is MainUiState.LoggedIn -> {
            val email = (viewModel.uiState.value as MainUiState.LoggedIn).emailId
            LoggedIn(email)
        }
    }

}