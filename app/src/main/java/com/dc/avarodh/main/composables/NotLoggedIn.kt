package com.dc.avarodh.main.composables

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dc.avarodh.R
import com.firebase.ui.auth.AuthUI

@Composable
fun NotLoggedIn(loginLauncher: ActivityResultLauncher<Intent>) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Welcome to Avarodh")

        Button(onClick = { fireLoginIntent(loginLauncher) }) {
            Text(text = "Login")
        }

    }
}

fun fireLoginIntent(loginLauncher: ActivityResultLauncher<Intent>) {
    val intent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(listOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        ))
        .setTheme(R.style.FirebaseUI_DefaultMaterialTheme)
        .build()
    loginLauncher.launch(intent)
}