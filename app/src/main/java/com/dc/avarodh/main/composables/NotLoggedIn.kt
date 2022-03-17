package com.dc.avarodh.main.composables

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dc.avarodh.R
import com.firebase.ui.auth.AuthUI

@Composable
fun NotLoggedIn(loginLauncher: ActivityResultLauncher<Intent>) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Welcome to Kavach",
        modifier = Modifier.padding(16.dp),
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
        )

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