package com.dc.avarodh

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dc.avarodh.main.MainScreen
import com.dc.avarodh.main.MainUiState
import com.dc.avarodh.main.MainViewModel
import com.dc.avarodh.ui.theme.AvarodhTheme
import com.google.firebase.auth.FirebaseAuth


class MainActivity : ComponentActivity() {

    private lateinit var loginLauncher: ActivityResultLauncher<Intent>
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AvarodhTheme {
                MainScreen(
                    loginLauncher, viewModel
                )
            }
        }

        setupLoginLauncher()
        getLoggedInEmailFromPrefs()
    }

    private fun setupLoginLauncher() {
        loginLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                user?.let {
                    val email = it.email!!
                    viewModel.uiState.value = MainUiState.LoggedIn(email)
                    saveEmailToPrefs(email)
                }
            }
        }
    }

    private fun getLoggedInEmailFromPrefs() {
        val prefs = getSharedPreferences("main", MODE_PRIVATE)
        val email =  prefs.getString("emailId", null)
        email?.let { 
            viewModel.uiState.value = MainUiState.LoggedIn(it)
            viewModel.fetchList()
        }
    }

    private fun saveEmailToPrefs(email: String) {
        val prefs = getSharedPreferences("main", MODE_PRIVATE)
        prefs.edit().putString("emailId", email).apply()
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AvarodhTheme {

    }
}




