package com.dc.kavach.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.dc.kavach.ui.theme.KavachTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var loginLauncher: ActivityResultLauncher<Intent>
    private val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KavachTheme {
                Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = { Text(text = "77_Kavach") })
                        }
                    ) {
                        //Navigation()
                        MainScreen(loginLauncher, viewmodel)
                    }
                }
            }
        }

        setupLoginLauncher()
    }

    private fun setupLoginLauncher() {
        loginLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                user?.let {
                    val email = it.email!!

                    viewmodel.uiState.value = MainUiState.LoggedIn(email)
                    viewmodel.userRepository.saveEmailToPrefs(email)
                    viewmodel.fetchList()

                    Toast.makeText(
                        this,
                        "Congratulation! You have logged in as $email",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        if (viewmodel.uiState.value is MainUiState.FilteredAppList) {
            viewmodel.refreshList()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KavachTheme {

    }
}




