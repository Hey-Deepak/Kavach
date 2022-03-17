package com.dc.kavach

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dc.kavach.main.MainScreen
import com.dc.kavach.main.MainUiState
import com.dc.kavach.main.MainViewModel
import com.dc.kavach.ui.theme.KavachTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /*@Inject
    lateinit var packageManager: PackageManager.Property*/
    @Inject
    lateinit var string: String
    @Inject
    lateinit var pm: PackageManager

    private lateinit var loginLauncher: ActivityResultLauncher<Intent>
    private val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", string)
        Log.d("TAG", pm.toString())
        setContent {
            KavachTheme {
               // viewmodel = viewModel()
                Scaffold(
                    topBar = {
                        TopAppBar { Text(text = "Kavach") }
                    }
                ) {
                    MainScreen(
                        loginLauncher, viewmodel
                    )
                }
                
            }
        }

        setupLoginLauncher()
        getLoggedInEmailFromPrefs()
        //viewmodel.pushList()

    }

    private fun setupLoginLauncher() {
        loginLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                user?.let {
                    val email = it.email!!
                    viewmodel.uiState.value = MainUiState.LoggedIn(email)
                    saveEmailToPrefs(email)
                    viewmodel.fetchList()
                    Toast.makeText(this, "Congratulation! You have logged in as $email", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getLoggedInEmailFromPrefs() {
        val prefs = getSharedPreferences("main", MODE_PRIVATE)
        val email =  prefs.getString("emailId", null)
        email?.let { 
            viewmodel.uiState.value = MainUiState.LoggedIn(it)
            viewmodel.fetchList()
        }
    }

    private fun saveEmailToPrefs(email: String) {
        val prefs = getSharedPreferences("main", MODE_PRIVATE)
        prefs.edit().putString("emailId", email).apply()
    }


    override fun onResume() {
        super.onResume()
        if (viewmodel.uiState.value is MainUiState.FilteredAppList){
        Log.d("TAG11", "Hi")
            val localAppList = viewmodel.getLocalappList()
            viewmodel.filterList(localAppList)
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KavachTheme {

    }
}




