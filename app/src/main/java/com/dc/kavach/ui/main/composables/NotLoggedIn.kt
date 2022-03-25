package com.dc.kavach.ui.main.composables

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dc.kavach.R
import com.firebase.ui.auth.AuthUI

@Composable
fun NotLoggedIn(loginLauncher: ActivityResultLauncher<Intent>) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_tank_app_icon), contentDescription = "",
        modifier = Modifier.fillMaxSize(0.6f))

        Text(text = "Welcome to 77_Kavach",
        modifier = Modifier.padding(16.dp),
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold
        )

        Button(onClick = { fireLoginIntent(loginLauncher) }) {
            Text(text = "Login",
            fontSize = 20.sp)
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

